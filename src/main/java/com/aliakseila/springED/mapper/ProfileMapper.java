package com.aliakseila.springED.mapper;

import com.aliakseila.springED.model.dto.ProfileDto;
import com.aliakseila.springED.model.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProfileMapper {

    public static final ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    public abstract Profile mapToEntity(ProfileDto profileDto);

    public abstract ProfileDto mapToDto(Profile profile);
}
