package com.jamilis.login.service;

import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.entity.UserEntity;

public interface ILoginService {

    /**
     * Creates BCI user account
     * @param signUpRequestDto signUpRequestDto
     * @return user token for provided user info
     */
    SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto);

    /**
     * Get BCI user by token
     * @param token login token
     * @return user info for provided token
     */
    LoginResponseDto loginUser(String token);
}
