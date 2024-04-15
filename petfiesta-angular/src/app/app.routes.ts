import { Routes } from '@angular/router';
import {SigninComponent} from "./signin/signin.component";
import {SignupComponent} from "./signup/signup.component";
import {PetProfileComponent} from "./pet-profile/pet-profile.component";
import {PetProfileCreateComponent} from "./pet-profile-create/pet-profile-create.component";
import {PetProfileUpdateComponent} from "./pet-profile-update/pet-profile-update.component";
import {TagsComponent} from "./tags/tags.component";
import {TagCreateComponent} from "./tag-create/tag-create.component";
import {TagUpdateComponent} from "./tag-update/tag-update.component";

export const routes: Routes = [
    {
        path: 'signin',
        component: SigninComponent
    },
    {
        path: 'signup',
        component: SignupComponent
    },
    {
        path: 'pet-profiles',
        component: PetProfileComponent
    },
    {
        path: 'pet-profiles/create',
        component: PetProfileCreateComponent
    },
    {
        path: 'pet-profiles/update/:id',
        component: PetProfileUpdateComponent
    },
    {
        path: 'tags',
        component: TagsComponent
    },
    {
        path: 'tags/create',
        component: TagCreateComponent
    },
    {
        path: 'tags/update/:id',
        component: TagUpdateComponent
    },


];
