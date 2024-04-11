package com.dburackov.petfiesta.controllers;


import com.dburackov.petfiesta.dto.passport.PassportDto;
import com.dburackov.petfiesta.services.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/passport")
@RestController
public class PassportController {
    private final PassportService passportService;

    @Autowired
    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/{id}")
    public PassportDto getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public PassportDto createPassport(@RequestBody  PassportDto passportDto,
                                   Principal principal)
    {
        return passportService.createPassport(passportDto, Long.parseLong(principal.getName()));
    }

    @PostMapping("update/{id}")
    @PreAuthorize("isAuthenticated()")
    public PassportDto updatePassport(@PathVariable Long id,
                                   @RequestBody PassportDto passportDto,
                                   Principal principal)
    {
        return passportService.updatePassport(id, passportDto, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deletePassport(@PathVariable Long id,
                               Principal principal)
    {
        passportService.deletePassportById(id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/pet-profile/{petProfileId}")
    public PassportDto getPassportByPetProfileId(@PathVariable Long petProfileId) {
        return passportService.getPassportByPetProfileId(petProfileId);
    }

}
