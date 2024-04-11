import axios from "axios"

export async function getPetProfiles() {
    let response = await axios.get(
        'http://localhost:8080/pet-profiles'
    )
    return response.data
}

export async function getPetProfile(id) {
    let response = await axios.get(
        `http://localhost:8080/pet-profiles/${id}`
    )
    return response.data
}

export async function deletePetProfile(id, token) {
    const config = {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }
    let response = await axios.delete(
        `http://localhost:8080/pet-profiles/delete/${id}`,
        config
    )
    return response.data
}

export async function createPetProfile(petProfile, token) {
    const config = {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }
    let response = await axios.post(
        `http://localhost:8080/pet-profiles/create`,
        petProfile,
        config
    )
    return response.data
}

export async function updatePetProfile(petProfile, token) {
    const config = {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }
    let response = await axios.post(
        `http://localhost:8080/pet-profiles/update/${petProfile.id}`,
        petProfile,
        config
    )
    return response.data
}

export async function getPetProfilesByUserId(userId) {
    let response = await axios.get(
        `http://localhost:8080/pet-profiles/user/${userId}`
    )
    return response.data
}