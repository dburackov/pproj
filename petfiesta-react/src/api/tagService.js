import axios from "axios"

export async function getTags() {
    let response = await axios.get(
        'http://localhost:8080/tags'
    )
    return response.data
}

export async function getTag(id) {
    let response = await axios.get(
        'http://localhost:8080/tags/' + id
    )
    return response.data
}

export async function deleteTag(id) {
    let response = await axios.delete(
        'http://localhost:8080/tags/delete/' + id
    )
    return response.data
}

export async function createTag(tag) {
    let response = await axios.post(
        'http://localhost:8080/tags/create',
        tag
    )
    return response.data
}

export async function updateTag(id, tag) {
    let response = await axios.post(
        'http://localhost:8080/tags/update/' + id,
        tag
    )
    return response.data
}

export async function getTagsByPetProfileId(petProfileId) {
    let response = await axios.get(
        'http://localhost:8080/pet-profiles/' + petProfileId + '/tags'
    )
    return response.data
}