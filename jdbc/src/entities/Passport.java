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

    public int passportId;
    public int petProfileId;
    public String name;
    public Date birthDate;
    public Kind kind;
    public String breed;
    public boolean breedingCertificate;
    public String coat;
    public String bio;

}
