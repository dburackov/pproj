export class ModifyTag {
    constructor(tagId: number, petProfileId: number) {
        this.tagId = tagId;
        this.petProfileId = petProfileId;
    }

    tagId!: number;
    petProfileId!: number;
}
