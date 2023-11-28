package com.jamilis.login.mapper;

import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = IPhoneMapper.class)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "lastLogin", expression = "java(java.time.Instant.now())")
    @Mapping(target = "isActive", constant = "true")
    UserEntity mapToEntity(SignUpRequestDto dto);

    SignUpResponseDto mapToSignUpResponse(UserEntity entity);

    LoginResponseDto mapToLoginResponse(UserEntity entity);
}
