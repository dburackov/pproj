package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
public class Passport implements Entity {

    @Setter
    @Getter
    private Long passportId;

    @Setter
    @Getter
    private Long petProfileId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private LocalDate birthDate;

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
