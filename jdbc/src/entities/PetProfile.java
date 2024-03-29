package entities;

import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    private int petProfileId;

    @Getter
    @Setter
    private int userId;

    @Getter
    @Setter
    private Purpose purpose;

}
