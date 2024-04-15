import {Passport} from "./passport";
import {Tag} from "./tag";

export class PetProfile {
    id!: number;
    purpose!: String;
    passport!: Passport;
    tags!: Tag[];
}
