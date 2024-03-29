package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.repositories.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PassportService {
    @Autowired
    PassportRepository passportRepository;

    public PassportService() {
        //because
    }

    public Passport addPassport(Passport passport) {
        return passportRepository.save(passport);
    }

    public Passport updatePassport(UUID id, Passport passport) {
        passport.setPassportId(id);
        return passportRepository.save(passport);
    }

    public void deletePassportById(UUID id) {
        passportRepository.deleteById(id);
    }

    public Passport getPetPassport(UUID petProfileId) {
        return passportRepository.findByPetProfileId(petProfileId);
    }
}
