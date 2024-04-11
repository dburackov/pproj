import React, {useContext, useState} from "react";
import {AppContext} from "../contexts/contexts";
import {useNavigate} from "react-router-dom";
import Select from "react-select";
import {createPetProfile} from "../api/petProfileService";
import {createPassport} from "../api/passportService";
import {addTagToPetProfile, getTags} from "../api/tagService";



export function PetProfileCreate() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [name, setName] = useState('')
    const [birthDate, setBirthDate] = useState('')
    const [kind, setKind] = useState('')
    const [breed, setBreed] = useState('')
    const [bio, setBio] = useState('')
    const [purpose, setPurpose] = useState('');
    const [tags, setTags] = useState([]);

    let tagsList = []

    loadTags()

    let purposeList = [
        {value: 'WALKING', label: 'Walking'},
        {value: 'PROCREATION', label: 'Procreation'}
    ]
    let kindList = [
        {value: 'DOG', label: 'Dog'},
        {value: 'CAT', label: 'Cat'},
        {value: 'OTHER', label: 'Other'},
    ]

    const handleSelectPurpose = (selectedOption) => {
        setPurpose(selectedOption);
    };

    const handleSelectKind = (selectedOption) => {
        setKind(selectedOption);
    };

    const handleSelectTags = (selectedOptions) => {
        setTags(selectedOptions)
    }

    return (
        <section>
            <div className="form">
                <input
                    placeholder="name"
                    className="form-control"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <input
                    placeholder="birth date"
                    className="form-control"
                    type="date"
                    onChange={(e) => setBirthDate(e.target.value)}
                />
                <Select
                    options={kindList}
                    placeholder="kind"
                    value={kind}
                    isSearchable={true}
                    onChange={handleSelectKind}
                />
                <input
                    placeholder="breed"
                    className="form-control"
                    type="text"
                    onChange={(e) => setBreed(e.target.value)}
                />
                <input
                    placeholder="bio"
                    className="form-control"
                    type="text"
                    onChange={(e) => setBio(e.target.value)}
                />
                <Select
                    options={purposeList}
                    placeholder="purpose"
                    value={purpose}
                    isSearchable={true}
                    onChange={handleSelectPurpose}
                />

                <Select
                    options={tagsList}
                    placeholder="Tags"
                    value={tags}
                    onChange={handleSelectTags}
                    isSearchable={true}
                    isMulti
                />

                <button onClick={createPetProfileClick}>
                    Create
                </button>
            </div>
        </section>
    );

    async function createPetProfileClick() {
        const token = appContext.cookies.token

        let petProfile = {}
        petProfile.userId = appContext.cookies.userId
        petProfile.purpose = purpose.value
        petProfile = await createPetProfile(petProfile, token)

        let passport = {
            petProfileId: petProfile.id,
            name: name,
            birthDate: birthDate,
            kind: kind.value,
            breed: breed,
            bio: bio
        }
        await createPassport(passport, token)

        for (const tag of tags) {
            await addTagToPetProfile({ tagId: tag.value, petProfileId: petProfile.id }, token);
        }

        navigate('/pet-profiles')
    }

    async function loadTags() {
        const tags = await getTags();
        tags.map(tag => tagsList.push({value: tag.id, label: tag.name}))
    }
}