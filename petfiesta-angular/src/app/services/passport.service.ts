import { Injectable } from '@angular/core';
import axios from 'axios';
import {Passport} from "../entities/passport";

@Injectable({
    providedIn: 'root'
})
export class PassportService {
    constructor() { }

    async getPassportByPetProfileId(petProfileId: number) {
        const response = await axios.get(
            `http://localhost:8080/passport/pet-profile/${petProfileId}`
        )
        return response.data
    }

    async createPassport(passport: Passport, token: string) {
        const config = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }
        const response = await axios.post(
            `http://localhost:8080/passport/create`,
            passport,
            config
        )
        return response.data
    }

    async updatePassport(passport: Passport, token: string) {
        const config = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }
        const response = await axios.post(
            `http://localhost:8080/passport/update/${passport.id}`,
            passport,
            config
        )
        return response.data
    }

}
