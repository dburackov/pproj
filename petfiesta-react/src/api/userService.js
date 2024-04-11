import axios from "axios"

export async function getUsers() {
    let response = await axios.get(
        "http://localhost:8080/users"
    )
    return response.data
}

export async function getUserById(id) {
    let response = await axios.get(
        `http://localhost:8080/users/${id}`
    )
    return response.data
}

export async function deleteUser(id) {
    let response = await axios.delete(
        `http://localhost:8080/users/delete${id}`
    )
    return response.data
}

export async function createUser(user) {
    let response = await axios.post(
        'http://localhost:8080/users/create',
        user
    )
    return response.data
}

export async function updateUser(user) {
    let response = await axios.post(
        `http://localhost:8080/users/update/${user.id}`,
        user
    )
    return response.data
}