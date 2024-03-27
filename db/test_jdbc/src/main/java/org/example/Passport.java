package org.example;

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
        this.pet_profile_id = pet_profile_id;
        this.name = name;
        this.birth_date = birth_date;
        this.kind = kind;
        this.breed = breed;
        this.breeding_certificate = breeding_certificate;
        this.coat = coat;
        this.bio = bio;
    }

    public int passport_id = 0;
    public int pet_profile_id = 0;
    public String name = null;
    public Date birth_date = null;
    public Kind kind = null;
    public String breed = null;
    public boolean breeding_certificate = false;
    public String coat = null;
    public String bio = null;

    public static String sequence = "passport_passport_id_seq";
}
