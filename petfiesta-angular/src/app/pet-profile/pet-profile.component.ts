import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgFor} from "@angular/common";
import {PetProfile} from "../entities/pet-profile";
import {Passport} from "../entities/passport";
import {PetProfileService} from "../services/pet-profile.service";
import {PassportService} from "../services/passport.service";
import moment from "moment";
import {TagService} from "../services/tag.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-pet-profile',
  standalone: true,
  imports: [RouterLink, NgFor],
  templateUrl: './pet-profile.component.html'
})

export class PetProfileComponent implements OnInit {
    petProfiles: PetProfile[] = [];
    passports: Passport[] = [];
    private userId: string;
    private token: string

    constructor(private petProfileService: PetProfileService,
                private passportService: PassportService,
                private tagService: TagService,
                private cookieService: CookieService)
    {
        this.userId = cookieService.get('userId');
        this.token = cookieService.get('token');
    }

    async ngOnInit() {
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

    public readonly moment = moment;
}
