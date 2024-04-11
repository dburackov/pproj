package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.constants.Constants;
import com.dburackov.petfiesta.dto.passport.PassportDto;
import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.exceptions.NotFoundException;
import com.dburackov.petfiesta.mappers.PassportMapper;
import com.dburackov.petfiesta.repositories.PassportRepository;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class PassportService {
    private final PassportRepository passportRepository;
    private final PetProfileRepository petProfileRepository;
    private final PassportMapper passportMapper;

    @Autowired
    public PassportService(PassportRepository passportRepository,
                           PetProfileRepository petProfileRepository,
                           PassportMapper passportMapper)
    {
        this.passportRepository = passportRepository;
        this.petProfileRepository = petProfileRepository;
        this.passportMapper = passportMapper;
    }

    public PassportDto getPassportById(Long id) {
        Passport passport =  passportRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        return passportMapper.passportToPassportDto(passport);
    }

    public PassportDto createPassport(PassportDto passportDto, Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(passportDto.getPetProfileId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }

        Passport passport = passportMapper.passportDtoToPassport(passportDto);
        passport.setPetProfile(petProfile);

        return passportMapper.passportToPassportDto(passportRepository.save(passport));
    }

    public PassportDto updatePassport(Long id, PassportDto passportDto , Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(passportDto.getPetProfileId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }
        Passport passport = passportMapper.passportDtoToPassport(passportDto);
        passport.setId(id);
        passport.setPetProfile(petProfile);

        return passportMapper.passportToPassportDto(passportRepository.save(passport));
    }

    public void deletePassportById(Long id, Long authenticatedUserId) {
//        PetProfile petProfile = petProfileRepository.findByPassportId(id);
        Passport passport = passportRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(passport.getPetProfile().getUser().getId())) {
            throw new AccessDeniedException("");
        }
        passportRepository.deleteById(id);
    }

    public PassportDto getPassportByPetProfileId(Long petProfileId) {
        return passportMapper.passportToPassportDto(passportRepository.findByPetProfileId(petProfileId));
    }
}
