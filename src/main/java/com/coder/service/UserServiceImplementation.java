package com.coder.service;

import org.springframework.stereotype.Service;

import com.coder.config.JwtProvider;
import com.coder.exception.UserException;
import com.coder.model.User;
import com.coder.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	private UserRepository userRepository;
	
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
