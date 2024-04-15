import { Injectable } from '@angular/core';
import axios from 'axios';
import {PetProfile} from "../entities/pet-profile";

@Injectable({
    providedIn: 'root'
})
export class PetProfileService {
    constructor() { }

    async getPetProfiles() {
        const response = await axios.get(
            '/pet-profiles'
        )
        return response.data
    }

    async getPetProfile(id: number) {
        const response = await axios.get(
            `/pet-profiles/${id}`
        )
        return response.data
    }

    async deletePetProfile(id: number, token: string) {
        const config = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }
        const response = await axios.delete(
            `/pet-profiles/delete/${id}`,
            config
        )
        return response.data
    }

    async createPetProfile(petProfile: PetProfile, token: string) {
        const config = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }
        const response = await axios.post(
            `/pet-profiles/create`,
            petProfile,
            config
        )
        return response.data
    }

    async updatePetProfile(petProfile: PetProfile, token: string) {
        const config = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }
        const response = await axios.post(
            `/pet-profiles/update/${petProfile.id}`,
            petProfile,
            config
        )
        return response.data
    }

    async getPetProfilesByUserId(userId: string) {
        const response = await axios.get(
            `/pet-profiles/user/${userId}`
        )
        return response.data
    }

}
