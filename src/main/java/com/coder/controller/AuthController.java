package com.coder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.model.Cart;
import com.coder.model.User;
import com.coder.config.JwtProvider;
import com.coder.exception.UserException;
import com.coder.repository.UserRepository;
import com.coder.request.LoginRequest;
import com.coder.response.AuthResponse;
import com.coder.service.CartService;
import com.coder.service.CustomeUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private PasswordEncoder passwordEncoder;
	
	private CustomeUserServiceImplementation customeUserServiceImplementation;
	
	private JwtProvider jwtProvider;
	
	private UserRepository userRepository;
	
	private CartService cartService;
	
	public AuthController(UserRepository userRepository,CartService cartService, CustomeUserServiceImplementation customeUserServiceImplementation, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
		this.userRepository =  userRepository;
		this.customeUserServiceImplementation = customeUserServiceImplementation;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.cartService=cartService;
	}
  
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
		
		String email = user.getEmail();
		
		String password = user.getPassword();
		
		String firstName = user.getFirstName();
		
		String lastName = user.getLastName();
		
		com.coder.model.User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist != null) {
			throw new UserException("Email is already used with another account");
		}
		
	     User createdUser = new User();
	     createdUser.setEmail(email);
	     createdUser.setPassword(passwordEncoder.encode(password));
	     createdUser.setFirstName(firstName);
	     createdUser.setLastName(lastName);
	     
	     User savedUser = userRepository.save(createdUser);
	     Cart cart = cartService.createCart(savedUser);
	     
	     Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
	     
	     SecurityContextHolder.getContext().setAuthentication(authentication);
	    
	     
	     String token = jwtProvider.generateToken(authentication);
	     
	  AuthResponse authResponse = new AuthResponse();
	  authResponse.setJwt(token);
	  authResponse.setMessage("Signup successful");
	     
	     return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username, password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		 AuthResponse authResponse = new AuthResponse();
		  authResponse.setJwt(token);
		  authResponse.setMessage("Signin successful");
		  
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.ACCEPTED);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customeUserServiceImplementation.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password...");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}































