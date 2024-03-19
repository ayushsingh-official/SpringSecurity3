package com.project.springSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springSecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	
}