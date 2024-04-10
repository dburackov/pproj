package com.dburackov.petfiesta.mappers;

import com.dburackov.petfiesta.dto.passport.PassportDto;
import com.dburackov.petfiesta.entities.Passport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    Passport passportDtoToPassport(PassportDto passportDto);

    PassportDto passportToPassportDto(Passport passport);
}
