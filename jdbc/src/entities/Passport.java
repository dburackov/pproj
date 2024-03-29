package entities;

import java.util.Date;

public class Passport {
    public Passport() {

    }

    public Passport(
        int petProfileId,
        String name,
        Date birthDate,
        Kind kind,
        String breed,
        boolean breedingCertificate,
        String coat,
        String bio
    ) {
        this.petProfileId = petProfileId;
        this.name = name;
        this.birthDate = birthDate;
        this.kind = kind;
        this.breed = breed;
        this.breedingCertificate = breedingCertificate;
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
