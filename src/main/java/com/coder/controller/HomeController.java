package com.coder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.coder.response.ApiResponse;

@RestController
public class HomeController {
	
	public ResponseEntity<ApiResponse> homeController(){
		ApiResponse res = new ApiResponse("Welcome To E-Commerce", true);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
