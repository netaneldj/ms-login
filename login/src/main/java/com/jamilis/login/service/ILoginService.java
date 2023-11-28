package com.jamilis.login.service;

import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.entity.UserEntity;

public interface ILoginService {
    SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto);
    LoginResponseDto loginUser(String token);
    UserEntity findUserByEmail(String email);
}
