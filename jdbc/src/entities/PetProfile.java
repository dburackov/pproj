package entities;

import lombok.Getter;
import lombok.Setter;

public class PetProfile {

    public PetProfile() {
        this.petProfileId = 0;
        this.userId = 0;
        this.purpose = Purpose.walking;
    }

    public PetProfile(int userId, Purpose purpose) {
        this.petProfileId = 0;
        this.userId = userId;
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
