package com.project.springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.springSecurity.model.Role;
import com.project.springSecurity.services.impl.UserDetailsServicesImpl;

@Configuration
public class MySecurityConfig {

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServicesImpl();
	}

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
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Authentication Filter
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.formLogin(Customizer.withDefaults());
//		http.httpBasic(Customizer.withDefaults());
//		http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
//		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/hello").authenticated());

		http.csrf(AbstractHttpConfigurer::disable);
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.name()).requestMatchers("/user/**")
				.hasAnyAuthority(Role.USER.name()).anyRequest().authenticated());

		http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		// do filter before BasicAuthenticationFilter class
//		http.addFilterBefore(new MyCustomFilter(), BasicAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
