package com.dburackov.petfiesta.controllers;


import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.services.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PassportController {
    @Autowired
    PassportService passportService;

    @GetMapping("passport/add")
    public Passport addPassport(@RequestBody  Passport passport) {
        return passportService.addPassport(passport);
    }

    @PostMapping("passport/update/{id}")
    public Passport updatePassport(@PathVariable Long id, @RequestBody Passport passport) {
        return passportService.updatePassport(id, passport);
    }

    @DeleteMapping("passport/delete/{id}")
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassportById(id);
    }

    @GetMapping("pet-profiles/{petProfileId}/passport")
    public Passport getPetPassport(@PathVariable Long petProfileId) {
        return passportService.getPetPassport(petProfileId);
    }

}
