package entities;

import lombok.Getter;
import lombok.Setter;

public class SocialMedia {
    public SocialMedia() {}

    public SocialMedia(int petProfileId, String link, SocialMediaType type) {
        this.petProfileId = petProfileId;
        this.link = link;
        this.type = type;
    }

    @Getter
    @Setter
    private int socialMediaId = 0;

    @Getter
    @Setter
    private int petProfileId = 0;

    @Getter
    @Setter
    private String link = null;

    @Getter
    @Setter
    private SocialMediaType type = null;

//    public static String sequence = "social_media_social_media_id_seq";
}
