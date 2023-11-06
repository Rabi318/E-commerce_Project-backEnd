package com.coder.service;

import com.coder.exception.UserException;
import com.coder.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	
	

}
