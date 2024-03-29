package entities;

public class Photo {
    public Photo() {}

    public Photo(int petProfileId, String fileLink) {
        this.petProfileId = petProfileId;
        this.fileLink = fileLink;
    }

    public int photoId;
    public int petProfileId;
    public String fileLink;

}
