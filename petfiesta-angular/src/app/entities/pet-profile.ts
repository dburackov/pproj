import {Passport} from "./passport";
import {Tag} from "./tag";

export class PetProfile {
    id!: number;
    userId!: number;
    purpose!: string;
    passport: Passport = new Passport();
    tags: Tag[] = [];
}
