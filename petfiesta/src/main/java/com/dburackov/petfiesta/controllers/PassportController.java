package com.dburackov.petfiesta.controllers;


import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.services.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
public class PassportController {
    private final PassportService passportService;

    @Autowired
    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/passport/{id}")
    public Passport getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @GetMapping("/pet-profiles/{petProfileId}/passport/create")
    @PreAuthorize("isAuthenticated()")
    public Passport createPassport(@PathVariable Long petProfileId,
                                   @RequestBody  Passport passport,
                                   Principal principal)
    {
        return passportService.createPassport(petProfileId, passport, Long.parseLong(principal.getName()));
    }

    @PostMapping("/passport/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public Passport updatePassport(@PathVariable Long id,
                                   @RequestBody Passport passport,
                                   Principal principal)
    {
        return passportService.updatePassport(id, passport, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("/pet-profiles/{petProfileId}/passport/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deletePassport(@PathVariable Long petProfileId,
                               @PathVariable Long id,
                               Principal principal)
    {
        passportService.deletePassportById(petProfileId, id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/pet-profiles/{petProfileId}/passport")
    public Passport getPassportByPetProfileId(@PathVariable Long petProfileId) {
        return passportService.getPassportByPetProfileId(petProfileId);
    }

}
