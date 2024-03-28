package com.project.springSecurity.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.springSecurity.dto.JwtResponseDTO;
import com.project.springSecurity.dto.LoginRequestDTO;
import com.project.springSecurity.dto.SignUpRequestDTO;
import com.project.springSecurity.model.User;
import com.project.springSecurity.repository.UserRepository;
import com.project.springSecurity.services.AuthenticationService;
import com.project.springSecurity.services.JWTServices;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public User signUpApi(SignUpRequestDTO signUpRequestDTO) {

		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(signUpRequestDTO, User.class);

		System.out.println("user : " + user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setRole(Role.ADMIN);
		userRepository.save(user);

		return user;
	}

	@Override
	public JwtResponseDTO AuthenticateAndGetToken(LoginRequestDTO loginRequestDto) {

		System.out.println("going for authentication");

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

		System.out.println("authentication : " + authentication.isAuthenticated());
		System.out.println("AUTHENTICATION DONE........");

		User user = new User();
		user.setEmail(loginRequestDto.getEmail());
//		user.setUse(loginRequestDto.getEmail());

		if (authentication.isAuthenticated()) {
			return JwtResponseDTO.builder().token(JWTServices.generateToken(user)).build();
		} else {
			System.out.println("user not found..........");
			throw new UsernameNotFoundException("invalid user request.....!!");
		}

	}

}