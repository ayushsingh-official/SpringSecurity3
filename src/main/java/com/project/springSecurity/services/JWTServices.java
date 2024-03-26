package com.project.springSecurity.services;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JWTServices {
	
	String generateToken(UserDetails userDetails);

	String extractUsername(String token);

	Boolean validateToken(String token, UserDetails userDetails);

	Date extractExpiration(String token);

	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);

}
