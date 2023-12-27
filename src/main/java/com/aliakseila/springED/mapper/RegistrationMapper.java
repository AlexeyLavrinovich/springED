package com.aliakseila.springED.mapper;

import com.aliakseila.springED.model.dto.RegistrationDto;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class RegistrationMapper {

    public static final RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    public abstract User mapToUser(RegistrationDto registrationDto);

    public abstract Profile mapToProfile(RegistrationDto registrationDto);
}
