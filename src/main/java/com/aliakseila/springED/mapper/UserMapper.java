package com.aliakseila.springED.mapper;

import com.aliakseila.springED.entity.User;
import com.aliakseila.springED.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User mapToEntity(UserModel customerModel);

    public abstract UserModel mapToModel(User customer);
}
