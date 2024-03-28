package entities;

public class Photo {
    public Photo() {}

    public Photo(int pet_profile_id, int file_link) {
        this.pet_profile_id = pet_profile_id;
        this.file_link = file_link;
    }

    public int photo_id = 0;
    public int pet_profile_id = 0;
    public int file_link = 0;

    public static String sequence = "photos_photo_id_seq";
}
