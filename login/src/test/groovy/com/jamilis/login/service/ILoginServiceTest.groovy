package com.jamilis.login.service

import com.auth0.jwt.exceptions.TokenExpiredException
import com.jamilis.login.LoginApplication
import com.jamilis.login.constants.UserConstants
import com.jamilis.login.exception.UserAlreadyExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.temporal.ChronoUnit

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = LoginApplication)
class ILoginServiceTest extends Specification {
    @Autowired
    private LoginService loginService

    def "sign up user ok"() {
        given:
        def signUpUserRequestDto = UserConstants.SIGN_UP_REQUEST_DTO
        when:
        def response = loginService.signUpUser(signUpUserRequestDto)
        then:
        noExceptionThrown()
        with(response) {
            id != null
            created != null
            lastLogin != null
            token != null
            isActive == true
        }

    }

    def "sign up throws user already exist exception"() {
        def signUpRequestDto = UserConstants.SIGN_UP_REQUEST_DTO
        when:
        loginService.signUpUser(signUpRequestDto)
        loginService.signUpUser(signUpRequestDto)
        then:
        thrown(UserAlreadyExistException)
    }

    def "login user ok"() {
        given:
        def signUpUserRequestDto = UserConstants.SIGN_UP_REQUEST_DTO2
        def signUpUserResponseDto = loginService.signUpUser(signUpUserRequestDto)
        when:
        Thread.sleep(1000);
        def loginResponseDto = loginService.loginUser(signUpUserResponseDto.token);
        then:
        noExceptionThrown()
        with(loginResponseDto) {
            id == signUpUserResponseDto.getId()
            created.truncatedTo(ChronoUnit.SECONDS) == signUpUserResponseDto.getCreated().truncatedTo(ChronoUnit.SECONDS)
            lastLogin != signUpUserResponseDto.getLastLogin()
            token != signUpUserResponseDto.getToken()
            isActive == true
            name == signUpUserRequestDto.getName()
            email == signUpUserRequestDto.getEmail()
            password == signUpUserRequestDto.getPassword()
            phones == signUpUserRequestDto.getPhones()
        }
    }

    def "login throws expired token exception"() {
        given:
        def expiredToken = UserConstants.EXPIRED_TOKEN;
        when:
        loginService.loginUser(expiredToken);
        then:
        thrown(TokenExpiredException)
    }
}
