package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
public class Passport implements Entity {

    private int passportId;

    private int petProfileId;

    private String name;

    private Date birthDate;

    private String kind;

    private String breed;

    private boolean breedingCertificate;

    private String coat;

    private String bio;

}
