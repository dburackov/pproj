package entities;

public class SocialMedia {
    public SocialMedia() {}

    public SocialMedia(int pet_profile, String link, SocialMediaType type) {
        this.pet_profile = pet_profile;
        this.link = link;
        this.type = type;
    }

    public int social_media_id = 0;
    public int pet_profile = 0;
    public String link = null;
    public SocialMediaType type = null;

    public static String sequence = "social_media_social_media_id_seq";
}
