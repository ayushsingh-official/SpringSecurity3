package com.project.springSecurity.dto;

import com.project.springSecurity.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestDTO {

	private String firstName;

	private String secondName;

	private String email;

	private String password;

//	@Enumerated(EnumType.STRING)
	private Role role;

}
