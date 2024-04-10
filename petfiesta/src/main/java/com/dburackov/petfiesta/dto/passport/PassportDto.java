package com.dburackov.petfiesta.dto.passport;

import com.dburackov.petfiesta.enums.Kind;
import lombok.Data;

import java.util.Date;

@Data
public class PassportDto {
    private Long id;
    private Long petProfileId;
    private String name;
    private Date birthDate;
    private Kind kind;
    private String breed;
    private Boolean breedingCertificate;
    private String coat;
    private String bio;
}
