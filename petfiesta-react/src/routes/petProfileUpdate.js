import {useLoaderData, useNavigate} from "react-router-dom";
import React, {useContext, useEffect, useState} from "react";
import Select from "react-select";
import {updatePetProfile} from "../api/petProfileService";
import {AppContext} from "../contexts/contexts";
import {getPassportByPetProfileId, updatePassport} from "../api/passportService";
import {addTagToPetProfile, deleteTagFromPetProfile, getTags, getTagsByPetProfileId} from "../api/tagService";



export function PetProfileUpdate() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const { petProfile } = useLoaderData();

    useEffect(() => {
        if (petProfile) {
            setPurpose(purposeList.find(item => item.value === petProfile.purpose))
            loadData()
        }
    }, [petProfile]);

    const [passportId, setPassportId] = useState();
    const [name, setName] = useState('')
    const [birthDate, setBirthDate] = useState('')
    const [kind, setKind] = useState({})
    const [breed, setBreed] = useState('')
    const [bio, setBio] = useState('')
    const [purpose, setPurpose] = useState({});
    const [tags, setTags] = useState([]);
    const [originalTags, setOriginalTags] = useState([]);

    const handleSelectKind = (selectedOption) => {
        setKind(selectedOption);
    };

    const handleSelectPurpose = (selectedOptions) => {
        setPurpose(selectedOptions);
    };

    const handleSelectTags = (selectedOptions) => {
        setTags(selectedOptions)
    }

    loadTags()

    let tagsList = []

    let purposeList = [
        {value: 'WALKING', label: 'Walking'},
        {value: 'PROCREATION', label: 'Procreation'}
    ]

    let kindList = [
        {value: 'DOG', label: 'Dog'},
        {value: 'CAT', label: 'Cat'},
        {value: 'OTHER', label: 'Other'},
    ]

    return (
        <section>
            <div className="form">
                <input
                    placeholder="name"
                    className="form-control"
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <input
                    placeholder="birth date"
                    className="form-control"
                    type="date"
                    value={birthDate}
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
                    value={breed}
                    onChange={(e) => setBreed(e.target.value)}
                />
                <textarea
                    placeholder="bio"
                    className="form-control"
                    type="text"
                    value={bio}
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


                <button onClick={updatePetProfileClick}>
                    Update
                </button>
            </div>
        </section>
    );

    async function updatePetProfileClick() {
        const token = appContext.cookies.token

        petProfile.userId = appContext.cookies.userId
        petProfile.purpose = purpose.value
        await updatePetProfile(petProfile, token)

        let passport = {
            petProfileId: petProfile.id,
            id: passportId,
            name: name,
            birthDate: birthDate,
            kind: kind.value,
            breed: breed,
            bio: bio
        };
        await updatePassport(passport, token)

        const tagsToAdd = tags.filter(tag => !originalTags.some(originalTag => tag.value === originalTag.value))

        for (const tag of tagsToAdd) {
            await addTagToPetProfile({ tagId: tag.value, petProfileId: petProfile.id }, token);
        }

        const tagsToDelete = originalTags.filter(originalTag => !tags.some(tag => tag.value === originalTag.value))

        for (const tag of tagsToDelete) {
            await deleteTagFromPetProfile({ tagId: tag.value, petProfileId: petProfile.id }, token);
        }

        navigate('/pet-profiles')
    }

    async function loadData() {
        const passport = await getPassportByPetProfileId(petProfile.id)
        setPassportId(passport.id)
        setName(passport.name)
        setBirthDate(passport.birthDate.substring(0, 10))
        setKind(kindList.find(item => item.value === passport.kind))
        setBreed(passport.breed)
        setBio(passport.bio)

        const tags = await getTagsByPetProfileId(petProfile.id)
        const normalizedTags = []
        tags.map(tag => normalizedTags.push({value: tag.id, label: tag.name}))
        setTags(normalizedTags)
        setOriginalTags(normalizedTags)
    }

    async function loadTags() {
        const tags = await getTags();
        tags.map(tag => tagsList.push({value: tag.id, label: tag.name}))
    }
}
