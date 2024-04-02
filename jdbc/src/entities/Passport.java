package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
public class Passport implements Entity {

    @Setter
    @Getter
    private int passportId;

    @Setter
    @Getter
    private int petProfileId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Date birthDate;

    @Setter
    @Getter
    private String kind;

    @Setter
    @Getter
    private String breed;

    @Setter
    @Getter
    private boolean breedingCertificate;

    @Setter
    @Getter
    private String coat;

    @Setter
    @Getter
    private String bio;

}
