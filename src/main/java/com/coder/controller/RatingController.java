package com.coder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.coder.exception.ProductException;
import com.coder.exception.UserException;
import com.coder.model.Rating;
import com.coder.model.User;
import com.coder.request.RatingRequest;
import com.coder.service.RatingService;
import com.coder.service.UserService;

public class RatingController {

	private UserService userService;
	private RatingService ratingService;
	
	public RatingController(UserService userService,RatingService ratingService) {
		this.ratingService = ratingService;
		this.userService = userService;		
	}
	
	@PostMapping("/create")
	public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req, @RequestHeader("Authorization")String jwt) throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		Rating rating = ratingService.createRating(req, user);
		return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductReviewHandler(@PathVariable Long productId){
		List<Rating> ratings = ratingService.getProductsRating(productId);
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
}
