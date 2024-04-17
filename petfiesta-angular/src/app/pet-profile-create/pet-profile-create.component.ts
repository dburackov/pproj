import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgFor} from "@angular/common";
import {PetProfile} from "../entities/pet-profile";
import {PetProfileService} from "../services/pet-profile.service";
import {TagService} from "../services/tag.service";
import {CookieService} from "ngx-cookie-service";
import {PassportService} from "../services/passport.service";
import {ModifyTag} from "../entities/modify-tag";
import {Router} from "@angular/router";
import {Tag} from "../entities/tag";
import {MatSelectModule} from '@angular/material/select';


@Component({
  selector: 'app-pet-profile-create',
  standalone: true,
  imports: [FormsModule, NgFor, MatSelectModule],
  templateUrl: './pet-profile-create.component.html'
})

export class PetProfileCreateComponent implements OnInit {
    petProfile: PetProfile = new PetProfile();
    tagsList: Tag[] = [];
    private userId: string;
    private token: string;

    purposeList = [
        { value: 'WALKING', label: 'Walking' },
        { value: 'PROCREATION', label: 'Procreation' }
    ];
    kindList = [
        { value: 'DOG', label: 'Dog' },
        { value: 'CAT', label: 'Cat' },
        { value: 'OTHER', label: 'Other' }
    ];


    constructor(private petProfileService: PetProfileService,
                private passportService: PassportService,
                private tagService: TagService,
                private cookieService: CookieService,
                private router: Router)
    {
        this.userId = cookieService.get('userId');
        this.token = cookieService.get('token');
        this.petProfile.userId = parseInt(this.userId);
    }

    async ngOnInit(){
        this.tagsList = await this.tagService.getTags();
    }

    async createPetProfile() {
        const newPetProfile = await this.petProfileService.createPetProfile(this.petProfile, this.token);

        this.petProfile.passport.petProfileId = newPetProfile.id;

        await this.passportService.createPassport(this.petProfile.passport, this.token);

        for (let tag of this.petProfile.tags) {
            console.log(tag);
            await this.tagService.addTagToPetProfile(new ModifyTag(tag.id, newPetProfile.id), this.token)
        }

        await this.router.navigate(["/pet-profiles"]);
    }
}
