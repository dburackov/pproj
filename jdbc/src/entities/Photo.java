package entities;

public class Photo {
    public Photo() {}

    public Photo(int pet_profile_id, String file_link) {
        this.petProfileId = pet_profile_id;
        this.fileLink = file_link;
    }

    public int photoId;
    public int petProfileId;
    public String fileLink;

}
