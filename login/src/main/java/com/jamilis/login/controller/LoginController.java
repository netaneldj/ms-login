package com.jamilis.login.controller;

import com.jamilis.login.dto.LoginResponseDto;
import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.dto.SignUpResponseDto;
import com.jamilis.login.service.ILoginService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private ILoginService service;

    /**
     * POST /sign-up : POST User. Creates a new user in BCI.
     * @param dto userDto
     * @return Success user token (status code 200) or Bad Request (status code 400) or Internal Server Error (status code 500)
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpRequestDto dto) {
        SignUpResponseDto responseDto = service.signUpUser(dto);
        return new ResponseEntity<SignUpResponseDto>(responseDto, HttpStatus.CREATED);
    }

    /**
     * GET /login : GET User. Get BCI user by token.
     * @param token loginToken
     * @return Success user info (status code 200) or Bad Request (status code 400) or Unauthorized (status code
     *      401) or Internal Server Error (status code 500)
     */
    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@AuthenticationPrincipal @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        LoginResponseDto responseDto = service.loginUser(token);
        return new ResponseEntity<LoginResponseDto>(responseDto, HttpStatus.OK);
    }

}
