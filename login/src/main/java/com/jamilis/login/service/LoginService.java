package com.jamilis.login.service;

import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.entity.UserEntity;
import com.jamilis.login.exception.UserAlreadyExistException;
import com.jamilis.login.mapper.IUserMapper;
import com.jamilis.login.repository.IUserRepository;
import com.jamilis.login.security.UserAuthenticationProvider;
import com.jamilis.login.utils.EncryptUtils;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Override
    public SignUpResponseDto signUpUser(SignUpRequestDto signUpRequestDto) {
        if (repository.findByEmailAndIsActiveTrue(signUpRequestDto.getEmail()).isPresent())
            throw new UserAlreadyExistException();
        UserEntity signUpRequestEntity = IUserMapper.INSTANCE.mapToEntity(signUpRequestDto);
        signUpRequestEntity.setPassword(EncryptUtils.encrypt(signUpRequestDto.getPassword(), signUpRequestDto.getEmail()));
        UserEntity userCreated = repository.save(signUpRequestEntity);
        SignUpResponseDto signUpResponseDto = IUserMapper.INSTANCE.mapToSignUpResponse(userCreated);
        signUpResponseDto.setToken(userAuthenticationProvider.createToken(signUpRequestDto.getEmail()));
        return signUpResponseDto;
    }

    @Override
    public LoginResponseDto loginUser(String token) {
        UserEntity userEntity = userAuthenticationProvider.getUserByToken(token);
        userEntity.setLastLogin(Instant.now());
        UserEntity userEntityUpdated = repository.save(userEntity);
        LoginResponseDto loginResponseDto = IUserMapper.INSTANCE.mapToLoginResponse(userEntityUpdated);
        loginResponseDto.setPassword(EncryptUtils.decrypt(userEntityUpdated.getPassword(),
                userEntityUpdated.getEmail()));
        loginResponseDto.setToken(userAuthenticationProvider.createToken(userEntityUpdated.getEmail()));
        return loginResponseDto;
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return repository.findByEmailAndIsActiveTrue(email).orElse(null);
    }
}
