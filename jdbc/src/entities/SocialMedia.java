package entities;

public class SocialMedia {
    public SocialMedia() {}

    public SocialMedia(int petProfileId, String link, SocialMediaType type) {
        this.petProfileId = petProfileId;
        this.link = link;
        this.type = type;
    }

    public int socialMediaId = 0;
    public int petProfileId = 0;
    public String link = null;
    public SocialMediaType type = null;

//    public static String sequence = "social_media_social_media_id_seq";
}
