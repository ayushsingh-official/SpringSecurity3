package com.project.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springSecurity.dto.SignUpRequestDTO;
import com.project.springSecurity.model.User;
import com.project.springSecurity.services.JWTServices;
import com.project.springSecurity.services.impl.AuthenticationServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired 
	private JWTServices JWTServices;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

//	@PostMapping("/login")
//	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
//	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
//	
//	    User userDetails = new User();
//	    userDetails.set
//	    
//	    
//	    if(authentication.isAuthenticated()){
//	       return JwtResponseDTO.builder()
//	               .accessToken(JWTServices.generateToken().build();
//	    } else {
//	        throw new UsernameNotFoundException("invalid user request..!!");
//	    }
//	}

	@PostMapping("/signUp")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

		System.out.println("controller sign..........");
		return ResponseEntity.ok(authenticationServiceImpl.signUpApi(signUpRequestDTO));

	}

}
