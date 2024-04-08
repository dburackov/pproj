import React, { useState, useEffect,useContext } from 'react';
import { AppContext } from "../contexts/contexts";
import {getPetProfile, getPetProfiles, getUserPetProfiles, createPetProfile, deletePetProfile, updatePetProfile} from '../api/petProfileService'
import { Link, useLoaderData, useNavigate } from "react-router-dom";
import userEvent from '@testing-library/user-event';


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
    }, []);    

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
                                User 
                            </th>
                            <th>
                                Purpose
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
                                    {petProfile.userId}
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
        const petProfiles = await getPetProfiles()
        setPetProfiles(petProfiles)
    }

    async function removePetProfile(id) {
        await deletePetProfile(id)
        setReload(!reload)
    }
}

export function PetProfilesCreate() {
    const navigate = useNavigate();

    const [userId, setUserId] = useState('');
    const [purpose, setPurpose] = useState('');

    return (
        <section>
            <div class="form">
                <input 
                    placeholder="user id"
                    class="form-control"
                    type="number"
                    onChange={(e) => setUserId(e.target.value)}
                />

                <input 
                    placeholder="purpose"
                    class="form-control"
                    type="text"
                    onChange={(e) => setPurpose(e.target.value)}
                />  
            
                <button onClick={createPetProfileClick}>
                    Create
                </button>
            </div>
        </section>
    );

    async function createPetProfileClick() {
        let petProfile = {}
        petProfile.userId = userId
        petProfile.purpose = purpose
        await createPetProfile(petProfile)
        navigate('/pet-profiles')
    }
}

export function PetProfilesUpdate() {
    const navigate = useNavigate();

    const { petProfile } = useLoaderData();

    const [purpose, setPurpose] = useState('');

    return (
        <section>
            <div class="form">
                <input 
                    placeholder="purpose"
                    class="form-control"
                    type="text"
                    onChange={(e) => setPurpose(e.target.value)}
                />  
            
                <button onClick={updatePetProfileClick}>
                    Update
                </button>
            </div>
        </section>
    );

    async function updatePetProfileClick() {
        petProfile.purpose = purpose
        await updatePetProfile(petProfile)
        navigate('/pet-profiles')
    }
}

