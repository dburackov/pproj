import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgFor} from "@angular/common";
import {MatSelectModule} from "@angular/material/select";
import {PetProfile} from "../entities/pet-profile";
import {Tag} from "../entities/tag";
import {PetProfileService} from "../services/pet-profile.service";
import {PassportService} from "../services/passport.service";
import {TagService} from "../services/tag.service";
import {CookieService} from "ngx-cookie-service";
import {ActivatedRoute, Router} from "@angular/router";
import {ModifyTag} from "../entities/modify-tag";
import {
    PartialInjectableLinkerVersion1
} from "@angular/compiler-cli/linker/src/file_linker/partial_linkers/partial_injectable_linker_1";

@Component({
  selector: 'app-pet-profile-update',
  standalone: true,
    imports: [FormsModule, NgFor, MatSelectModule],
  templateUrl: './pet-profile-update.component.html',
  styleUrl: './pet-profile-update.component.css'
})

export class PetProfileUpdateComponent {
    petProfile: PetProfile = new PetProfile();
    petProfileId: number;
    tagsList: Tag[] = [];
    originalTags: Tag[] = [];
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
                private activatedRoute: ActivatedRoute,
                private router: Router)
    {
        this.userId = cookieService.get('userId');
        this.token = cookieService.get('token');
        this.petProfileId = activatedRoute.snapshot.params["id"];
    }

    async ngOnInit(){
        this.petProfile = await this.petProfileService.getPetProfile(this.petProfileId);
        this.petProfile.userId = parseInt(this.userId);
        this.petProfile.passport = await this.passportService.getPassportByPetProfileId(this.petProfileId);
        this.petProfile.passport.petProfileId = this.petProfileId;
        this.petProfile.tags = await this.tagService.getTagsByPetProfileId(this.petProfileId);
        this.originalTags = this.petProfile.tags;
        this.tagsList = await this.tagService.getTags();
    }

    async updatePetProfile() {
        await this.petProfileService.updatePetProfile(this.petProfile, this.token);
        await this.passportService.updatePassport(this.petProfile.passport, this.token);

        let tags = this.petProfile.tags;
        const tagsToAdd: Tag[] = tags.filter(tag => !this.originalTags.some(originalTag => tag.id === originalTag.id));

        for (let tag of tagsToAdd) {
            await this.tagService.addTagToPetProfile(new ModifyTag(tag.id, this.petProfileId), this.token);
        }

        const tagsToDelete = this.originalTags.filter(originalTag => !tags.some(tag => tag.id === originalTag.id));
        
        for (let tag of tagsToDelete) {
            await this.tagService.deleteTagFromPetProfile(new ModifyTag(tag.id, this.petProfileId), this.token);
        }

        await this.router.navigate(["/pet-profiles"]);
    }

}
