package com.coder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.exception.ProductException;
import com.coder.exception.UserException;
import com.coder.model.Review;
import com.coder.model.User;
import com.coder.request.ReviewRequest;
import com.coder.service.ReviewService;
import com.coder.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	private ReviewService reviewService;
	
	private UserService userService;
	
	public ReviewController(ReviewService reviewService,UserService userService) {
		// TODO Auto-generated constructor stub
		this.reviewService = reviewService;
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReviewHandler(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user  = userService.findUserProfileByJwt(jwt);
		System.out.println("Product id "+req.getProductId()+" - "+req.getReview());
		Review review = reviewService.createReview(req, user);
		System.out.println("Product review "+req.getReview());
		return new ResponseEntity<Review>(review,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>>getProductsReviewHandler(@PathVariable Long prroductId){
		
		List<Review> reviews = reviewService.getAllReview(prroductId);
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.OK);
	}
}
