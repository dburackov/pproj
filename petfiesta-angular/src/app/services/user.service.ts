import { Injectable } from '@angular/core';
import axios from 'axios';
import User from "../entities/user";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    constructor() { }

    async getUsers() {
        const response = await axios.get(
            "/users"
        )
        return response.data
    }

    async getUserById(id: string) {
        const response = await axios.get(
            `/users/${id}`
        )
        return response.data
    }

    async deleteUser(id: number) {
        const response = await axios.delete(
            `/users/delete${id}`
        )
        return response.data
    }

    async createUser(user: User) {
        const response = await axios.post(
            '/users/create',
            user
        )
        return response.data
    }

    async updateUser(user: User) {
        const response = await axios.post(
            `/users/update/${user.id}`,
            user
        )
        return response.data
    }
}
