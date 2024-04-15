import { Injectable } from '@angular/core';
import axios from 'axios';
import {Tag} from "../entities/tag";
import {ModifyTag} from "../entities/modify-tag";

@Injectable({
    providedIn: 'root'
})
export class TagService {
    constructor() { }


    async getTags() {
        const response = await axios.get(
            '/tags'
        )
        return response.data
    }

    async getTag(id: number) {
        const response = await axios.get(
            `/tags/${id}`
        )
        return response.data
    }

    async deleteTag(id: number) {
        const response = await axios.delete(
            `/tags/delete/${id}`
        )
        return response.data
    }

    async createTag(tag: Tag) {
        const response = await axios.post(
            '/tags/create',
            tag
        )
        return response.data
    }

    async updateTag(id: number, tag: Tag) {
        const response = await axios.post(
            `/tags/update/${id}`,
            tag
        )
        return response.data
    }

    async getTagsByPetProfileId(petProfileId: number) {
        const response = await axios.get(
            `/tags/pet-profile/${petProfileId}`
        )
        return response.data
    }

    async addTagToPetProfile(tag: ModifyTag, token: string) {
        const config = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }
        const response = await axios.post(
            '/tags/add',
            tag,
            config
        )
        return response.data
    }

    async deleteTagFromPetProfile(tag: ModifyTag, token: string) {
        const config = {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }
        const response = await axios.post(
            '/tags/delete',
            tag,
            config
        )
        return response.data
    }


}
