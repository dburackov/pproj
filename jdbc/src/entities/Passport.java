package entities;

import java.util.Date;

public class Passport {
    public Passport() {

    }

    public Passport(
        int pet_profile_id,
        String name,
        Date birth_date,
        Kind kind,
        String breed,
        boolean breeding_certificate,
        String coat,
        String bio
    ) {
        this.petProfileId = pet_profile_id;
        this.name = name;
        this.birthDate = birth_date;
        this.kind = kind;
        this.breed = breed;
        this.breedingCertificate = breeding_certificate;
        this.coat = coat;
        this.bio = bio;
    }

    public int passportId = 0;
    public int petProfileId = 0;
    public String name = null;
    public Date birthDate = null;
    public Kind kind = null;
    public String breed = null;
    public boolean breedingCertificate = false;
    public String coat = null;
    public String bio = null;

}
