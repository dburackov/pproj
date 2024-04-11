import axios from "axios";

export async function getPassportByPetProfileId(petProfileId) {
    let response = await axios.get(
        `http://localhost:8080/passport/pet-profile/${petProfileId}`
    )
    return response.data
}

export async function createPassport(passport, token) {
    const config = {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }
    let response = await axios.post(
        `http://localhost:8080/passport/create`,
        passport,
        config
    )
    return response.data
}

export async function updatePassport(passport, token) {
    const config = {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }
    let response = await axios.post(
        `http://localhost:8080/passport/update/${passport.id}`,
        passport,
        config
    )
    return response.data
}