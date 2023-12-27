package com.aliakseila.springED.mapper;

import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User mapToEntity(UserDto customerModel);

    public abstract UserDto mapToDto(User customer);
}
