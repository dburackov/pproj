package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Passport {
    public Passport() {
    //because
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
