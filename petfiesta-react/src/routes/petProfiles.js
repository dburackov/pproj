import React, { useState, useEffect,useContext } from 'react';
import {AppContext} from "../contexts/contexts";
import {getPetProfile, getPetProfilesByUserId, deletePetProfile} from '../api/petProfileService'
import {Link, useNavigate} from "react-router-dom";
import {getPassportByPetProfileId} from '../api/passportService'
import {getTagsByPetProfileId} from '../api/tagService'
import moment from 'moment';


export async function loader({ params }) {
    const petProfile = await getPetProfile(params.petProfileId);
    return { petProfile };
}

export default function PetProfiles() {
    const appContext = useContext(AppContext);
    useNavigate();
    const [PetProfiles, setPetProfiles] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadPetProfiles();
    }, [reload]);

    return (
        <section className="main-content">
            <div className='table-title'>
                <h1>Pet Profiles</h1>
                <Link to="create/" className="btn btn-success">Create</Link>
                <table className='table table-striped table-hover'>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Birth date</th>
                            <th>Kind</th>
                            <th>Breed</th>
                            <th>Bio</th>
                            <th>Purpose</th>
                            <th>Tags</th>
                        </tr>
                    </thead>
                    <tbody>
                        {PetProfiles.map(petProfile =>
                            <tr key={petProfile.id}>
                                <td>{petProfile.passport.name}</td>
                                <td>{moment(petProfile.passport.birthDate).format('DD/MM/YYYY')}</td>
                                <td>{petProfile.passport.kind}</td>
                                <td>{petProfile.passport.breed}</td>
                                <td>{petProfile.passport.bio}</td>
                                <td>{petProfile.purpose}</td>
                                <td>{petProfile.tags.map(tag => tag.name + ' ')}</td>
                                <td>
                                    <Link to={`update/${petProfile.id}`} className="btn btn-outline-primary">Update</Link>
                                </td>
                                <td>
                                    <button 
                                        onClick={() => removePetProfile(petProfile.id)}
                                        value={petProfile.id}
                                        className="btn btn-outline-danger">
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
        let petProfiles = await getPetProfilesByUserId(appContext.cookies.userId)

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
        await deletePetProfile(id, appContext.cookies.token)
        setReload(!reload)
    }
}


