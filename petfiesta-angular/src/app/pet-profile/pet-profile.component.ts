import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {PetProfile} from "../entities/pet-profile";
import {Passport} from "../entities/passport";
import {PetProfileService} from "../services/pet-profile.service";
import {PassportService} from "../services/passport.service";
import {AuthorizationService} from "../services/authorization.service";
import moment from "moment";
import {TagService} from "../services/tag.service";

@Component({
  selector: 'app-pet-profile',
  standalone: true,
  imports: [RouterLink, NgFor],
  templateUrl: './pet-profile.component.html',
  styleUrl: './pet-profile.component.css'
})

export class PetProfileComponent {
    petProfiles: PetProfile[] = [];
    passports: Passport[] = [];
    userId: string;
    token: string

    constructor(private petProfileService: PetProfileService,
                private passportService: PassportService,
                private tagService: TagService,
                private authorizationService: AuthorizationService)
    {
        this.userId = authorizationService.userId;
        this.token = authorizationService.token;
    }

    async ngOnInit() {
        console.log("userId" + this.userId, this.authorizationService.userId);
        this.petProfiles = await this.petProfileService.getPetProfilesByUserId(this.userId);
        for (const petProfile of this.petProfiles) {
            petProfile.passport = await this.passportService.getPassportByPetProfileId(petProfile.id);
            petProfile.tags = await this.tagService.getTagsByPetProfileId(petProfile.id);
        }

    }

    async deletePetProfile(id: number) {
        await this.petProfileService.deletePetProfile(id, this.token);
        await this.ngOnInit();
    }

    async getPassport(petProfileId: number) {
        return this.passports.find(passport => passport.petProfileId === petProfileId);
    }

    protected readonly moment = moment;
}
