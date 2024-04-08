import axios from "axios"

export async function signIn(email, password) {
    let response = await axios.post(
        "http://localhost:8080/auth/signin", 
        {
            "email": email,
            "password": password
        }
    )
    return response.data
}

export async function signUp(login, email, password) {
    let response = await axios.post(
        "http://localhost:8080/auth/signup", 
        {
            "name": login,
            "email": email,
            "password": password
        }
    )
    return response.data
}