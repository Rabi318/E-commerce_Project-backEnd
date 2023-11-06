package com.coder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coder.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);

}
