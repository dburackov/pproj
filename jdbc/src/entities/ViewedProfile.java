package entities;

public class ViewedProfile {
    public ViewedProfile() {

    }

    public ViewedProfile(int objectPetId, int targetPetId) {
        this.objectPetId = objectPetId;
        this.targetPetId = targetPetId;
    }

    public int viewedProfileId = 0;
    public int objectPetId = 0;
    public int targetPetId = 0;

//    public static String sequence = "viewed_profiles_viewed_profile_id_seq";
}
