package entities;

public class ViewedProfile {
    public ViewedProfile() {

    }

    public ViewedProfile(int object_pet_id, int target_pet_id) {
        this.object_pet_id = object_pet_id;
        this.target_pet_id = target_pet_id;
    }

    public int viewed_profile_id = 0;
    public int object_pet_id = 0;
    public int target_pet_id = 0;

    public static String sequence = "viewed_profiles_viewed_profile_id_seq";
}
