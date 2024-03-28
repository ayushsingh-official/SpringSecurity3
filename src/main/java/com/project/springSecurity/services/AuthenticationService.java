package com.project.springSecurity.services;

import org.springframework.web.bind.annotation.RequestBody;

import com.project.springSecurity.dto.JwtResponseDTO;
import com.project.springSecurity.dto.LoginRequestDTO;
import com.project.springSecurity.dto.SignUpRequestDTO;
import com.project.springSecurity.model.User;

public interface AuthenticationService {

	User signUpApi(SignUpRequestDTO signUpRequestDTO);

	JwtResponseDTO AuthenticateAndGetToken(@RequestBody LoginRequestDTO loginRequestDto);

}
