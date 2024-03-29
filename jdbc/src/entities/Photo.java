package entities;

import lombok.Getter;
import lombok.Setter;

public class Photo {
    public Photo() {}

    public Photo(int petProfileId, String fileLink) {
        this.petProfileId = petProfileId;
        this.fileLink = fileLink;
    }

    @Getter
    @Setter
    private int photoId;

    @Getter
    @Setter
    private int petProfileId;

    @Getter
    @Setter
    private String fileLink;

}
