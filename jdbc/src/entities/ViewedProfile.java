package entities;

public class ViewedProfile {
    public ViewedProfile() {

    }

    public ViewedProfile(int object_pet_id, int target_pet_id) {
        this.objectPetId = object_pet_id;
        this.targetPetId = target_pet_id;
    }

    public int viewedProfileId = 0;
    public int objectPetId = 0;
    public int targetPetId = 0;

//    public static String sequence = "viewed_profiles_viewed_profile_id_seq";
}
