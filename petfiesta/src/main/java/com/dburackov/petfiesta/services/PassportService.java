package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.repositories.PassportRepository;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class PassportService {
    private final PassportRepository passportRepository;
    private final UserRepository userRepository;

    @Autowired
    public PassportService(PassportRepository passportRepository,
                           UserRepository userRepository)
    {
        this.passportRepository = passportRepository;
        this.userRepository = userRepository;
    }

    public Passport getPassportById(Long id) {
        return passportRepository.findById(id).get();
    }

    public Passport createPassport(Long petProfileId, Passport passport, Long authenticatedUserId) {
        User user = userRepository.findByPetProfilesId(petProfileId);
        if (!authenticatedUserId.equals(user.getId())) {
            throw new AccessDeniedException("");
        }
        return passportRepository.save(passport);
    }

    public Passport updatePassport(Long id, Passport passport, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(passport.getPetProfile().getUser().getId())) {
            throw new AccessDeniedException("");
        }
        passport.setId(id);
        return passportRepository.save(passport);
    }

    public void deletePassportById(Long petProfileId, Long id, Long authenticatedUserId) {
        User user = userRepository.findByPetProfilesId(petProfileId);
        if (!authenticatedUserId.equals(user.getId())) {
            throw new AccessDeniedException("");
        }
        passportRepository.deleteById(id);
    }

    public Passport getPassportByPetProfileId(Long petProfileId) {
        return passportRepository.findByPetProfileId(petProfileId);
    }
}
