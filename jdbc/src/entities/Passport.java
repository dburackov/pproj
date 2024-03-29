package entities;

import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    private int passportId;

    @Getter
    @Setter
    private int petProfileId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Date birthDate;

    @Getter
    @Setter
    private Kind kind;

    @Getter
    @Setter
    private String breed;

    @Getter
    @Setter
    private boolean breedingCertificate;

    @Getter
    @Setter
    private String coat;

    @Getter
    @Setter
    private String bio;

}
