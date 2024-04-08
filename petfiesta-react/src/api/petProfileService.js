import axios from "axios"

export async function getPetProfiles() {
    let response = await axios.get(
        'http://localhost:8080/pet-profiles'
    )
    return response.data
}

export async function getPetProfile(id) {
    let response = await axios.get(
        'http://localhost:8080/pet-profiles/' + id
    )
    return response.data
}

export async function deletePetProfile(userId, id) {
    let response = await axios.delete(
        'http://localhost:8080/users/' + userId + '/pet-profiles/delete' + id
    )
    return response.data
}

export async function createPetProfile(userId, petProfile) {
    let response = await axios.post(
        'http://localhost:8080/users/' + userId + '/pet-profiles/create',
        petProfile
    )
    return response.data
}

export async function updatePetProfile(userId, petProfile) {
    let response = await axios.post(
        'http://localhost:8080/users/' + userId + '/pet-profiles/update/' + petProfile.id,
        petProfile
    )
    return response.data
}

export async function getUserPetProfiles(userId) {
    let response = await axios.get(
        'http://localhost:8080/users/' + userId + '/pet-profiles'
    )
    return response.data
}