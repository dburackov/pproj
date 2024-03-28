package databaserepos;

public class PetProfile {

    public PetProfile() {
        this.pet_profile_id = 0;
        this.user_id = 0;
        this.purpose = Purpose.walking;
    }

    public PetProfile(int user_id, Purpose purpose) {
        this.pet_profile_id = 0;
        this.user_id = user_id;
        this.purpose = purpose;
    }

    public int pet_profile_id;
    public int user_id;

    public Purpose purpose;
    public static String sequence = "pet_profile_pet_profile_id_seq";

}
