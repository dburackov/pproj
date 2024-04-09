import axios from "axios";

export async function getPassportByPetProfileId(id) {
    let response = await axios.get(
        'http://localhost:8080/pet-profiles/' + id + '/passport'
    )
    return response.data
}