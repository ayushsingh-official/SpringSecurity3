package com.project.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

//	@Bean
//	UserDetailsService UserdetailService() {
//		InMemoryUserDetailsManager userDetails = new InMemoryUserDetailsManager();
//		UserDetails user = User.withUsername("ayush").password(passwordEncode().encode("ayush.singh@security"))
//				.authorities("read").build();
//
//		userDetails.createUser(user);
//		return userDetails;
//	}

	@Bean
	BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.httpBasic(Customizer.withDefaults());
//		http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/hello").authenticated());

		return http.build();
	}

}
