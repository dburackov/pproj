package entities;

import lombok.Getter;
import lombok.Setter;

public class ViewedProfile {
    public ViewedProfile() {

    }

    public ViewedProfile(int objectPetId, int targetPetId) {
        this.objectPetId = objectPetId;
        this.targetPetId = targetPetId;
    }

    @Getter
    @Setter
    private int viewedProfileId = 0;

    @Getter
    @Setter
    private int objectPetId = 0;

    @Getter
    @Setter
    private int targetPetId = 0;
    
}
