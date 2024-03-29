package entities;

public class SocialMedia {
    public SocialMedia() {}

    public SocialMedia(int pet_profile, String link, SocialMediaType type) {
        this.petProfile = pet_profile;
        this.link = link;
        this.type = type;
    }

    public int socialMediaId = 0;
    public int petProfile = 0;
    public String link = null;
    public SocialMediaType type = null;

    public static String sequence = "social_media_social_media_id_seq";
}
