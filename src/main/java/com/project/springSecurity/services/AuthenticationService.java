package com.project.springSecurity.services;

import com.project.springSecurity.dto.SignUpRequestDTO;
import com.project.springSecurity.model.User;

public interface AuthenticationService {

	User signUpApi(SignUpRequestDTO signUpRequestDTO);

}
