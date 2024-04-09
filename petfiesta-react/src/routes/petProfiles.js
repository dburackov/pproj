import React, { useState, useEffect,useContext } from 'react';
import { AppContext } from "../contexts/contexts";
import {getPetProfile, getPetProfiles, getUserPetProfiles, createPetProfile, deletePetProfile, updatePetProfile, } from '../api/petProfileService'
import { Link, useLoaderData, useNavigate } from "react-router-dom";
import {getPassportByPetProfileId} from '../api/passportService'
import {getTagsByPetProfileId} from '../api/tagService'
import moment from 'moment';
import Select from 'react-select';


export async function loader({ params }) {
    const petProfile = await getPetProfile(params.petProfileId);
    return { petProfile };
}

export default function PetProfiles() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [PetProfiles, setPetProfiles] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadPetProfiles();
    }, [reload]);

    return (
        <section>
            <div className='table-title'>
                <h2>
                    Pet Profiles
                </h2>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>
                                Name
                            </th>
                            <th>
                                Birth date
                            </th>
                            <th>
                                Kind
                            </th>
                            <th>
                                Breed
                            </th>
                            <th>
                                Bio
                            </th>
                            <th>
                                Purpose
                            </th>
                            <th>
                                Tags
                            </th>
                            <th>
                                <Link to="create/" className="btn btn-success">Create</Link>
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        {PetProfiles.map(petProfile =>
                            <tr key={petProfile.id}>
                                <td>
                                    {petProfile.passport.name}
                                </td>
                                <td>
                                    {moment(petProfile.passport.birthDate).format('DD/MM/YYYY')}
                                </td>
                                <td>
                                    {petProfile.passport.kind}
                                </td>
                                <td>
                                    {petProfile.passport.breed}
                                </td>
                                <td>
                                    {petProfile.passport.bio}
                                </td>
                                <td>
                                    {petProfile.purpose}
                                </td>
                                <td>
                                    {petProfile.tags.map(tag => tag.name + ' ')}
                                </td>
                                <td>
                                    <Link to={`update/${petProfile.id}`} className="btn btn-info">Update</Link>
                                    |
                                    <button 
                                        onClick={() => removePetProfile(petProfile.id)}
                                        value={petProfile.id}
                                        className="btn btn-danger">
                                            Remove
                                    </button>
                                </td>
                            </tr>
                        )}
                    </tbody>

                </table>
            </div>
        </section>
    );

    async function loadPetProfiles() {
        let petProfiles = await getUserPetProfiles(appContext.cookies.userId)

        const petProfilesWithPassport = await Promise.all(petProfiles.map(async (petProfile) => {
            const passport = await getPassportByPetProfileId(petProfile.id);
            const tags = await getTagsByPetProfileId(petProfile.id)
            return {
                ...petProfile,
                passport: passport,
                tags: tags
            };
        }));

        setPetProfiles(petProfilesWithPassport)
    }

    async function removePetProfile(id) {
        await deletePetProfile(appContext.cookies.userId, id, appContext.cookies.token)
        setReload(!reload)
    }
}

export function PetProfilesCreate() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [name, setName] = useState('')
    const [birthDate, setBirthDate] = useState('')
    const [kind, setKind] = useState('')
    const [breed, setBreed] = useState('')
    const [bio, setBio] = useState('')
    const [purpose, setPurpose] = useState('');

    const handleSelectPurpose = (selectedOptions) => {
        setPurpose(selectedOptions);
    };

    let purposeList = [
        {value: 'WALKING', label: 'Walking'},
        {value: 'PROCREATION', label: 'Procreation'}
    ]
    let kindList = [
        {value: 'DOG', label: 'dog'},
        {value: 'CAT', label: 'cat'},
        {value: 'OTHER', label: 'other'},
    ]

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
                    options={purposeList}
                    placeholder="purpose"
                    value={purpose}
                    isSearchable={true}
                    onChange={handleSelectPurpose}
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

                <button onClick={createPetProfileClick}>
                    Create
                </button>
            </div>
        </section>
    );

    async function createPetProfileClick() {
        let petProfile = {}
        petProfile.purpose = purpose
        await createPetProfile(appContext.cookies.userId, petProfile, appContext.cookies.token)
        navigate('/pet-profiles')
    }
}

export function PetProfilesUpdate() {
    const navigate = useNavigate();

    const { petProfile } = useLoaderData();

    const [purpose, setPurpose] = useState('');

    const handleSelectPurpose = (selectedOptions) => {
        setPurpose(selectedOptions);
    };

    let purposeList = [
        {value: 'WALKING', label: 'Walking'},
        {value: 'PROCREATION', label: 'Procreation'}
    ]

    return (
        <section>
            <div className="form">
                <Select
                    options={purposeList}
                    placeholder="purpose"
                    value={purpose}
                    isSearchable={true}
                    onChange={handleSelectPurpose}
                />  
            
                <button onClick={updatePetProfileClick}>
                    Update
                </button>
            </div>
        </section>
    );


    async function updatePetProfileClick() {
        petProfile.purpose = purpose
        await updatePetProfile(petProfile.id, petProfile)
        navigate('/pet-profiles')
    }
}

