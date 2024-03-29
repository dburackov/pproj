package entities;

public class PetProfile {

    public PetProfile() {
        this.petProfileId = 0;
        this.userId = 0;
        this.purpose = Purpose.walking;
    }

    public PetProfile(int user_id, Purpose purpose) {
        this.petProfileId = 0;
        this.userId = user_id;
        this.purpose = purpose;
    }

    public int petProfileId;
    public int userId;

    public Purpose purpose;

}
