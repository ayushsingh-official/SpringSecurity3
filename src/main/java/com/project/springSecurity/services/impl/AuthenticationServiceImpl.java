package com.project.springSecurity.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.springSecurity.dto.SignUpRequestDTO;
import com.project.springSecurity.model.Role;
import com.project.springSecurity.model.User;
import com.project.springSecurity.repository.UserRepository;
import com.project.springSecurity.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User signUpApi(SignUpRequestDTO signUpRequestDTO) {

		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(signUpRequestDTO, User.class);

		System.out.println("user : " + user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.ADMIN);
		userRepository.save(user);

		return user;
	}

}