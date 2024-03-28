package com.project.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springSecurity.dto.JwtResponseDTO;
import com.project.springSecurity.dto.LoginRequestDTO;
import com.project.springSecurity.dto.SignUpRequestDTO;
import com.project.springSecurity.model.User;
import com.project.springSecurity.services.impl.AuthenticationServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

	@GetMapping("/login")
	public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody LoginRequestDTO loginRequestDto) {

		return ResponseEntity.ok(authenticationServiceImpl.AuthenticateAndGetToken(loginRequestDto));

	}

	@PostMapping("/signUp")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

		System.out.println("controller sign..........");
		return ResponseEntity.ok(authenticationServiceImpl.signUpApi(signUpRequestDTO));

	}

}
