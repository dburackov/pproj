package entities;

public class Photo {
    public Photo() {}

    public Photo(int pet_profile_id, String file_link) {
        this.petProfileId = pet_profile_id;
        this.fileLink = file_link;
    }

    public int photoId = 0;
    public int petProfileId = 0;
    public String fileLink = null;

    public static String sequence = "photos_photo_id_seq";
}
