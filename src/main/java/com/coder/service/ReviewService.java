package com.coder.service;

import java.util.List;

import com.coder.exception.ProductException;
import com.coder.model.Review;
import com.coder.model.User;
import com.coder.request.ReviewRequest;

public interface ReviewService {

	public Review createReview (ReviewRequest req, User user)throws ProductException;
	
	public List<Review> getAllReview(Long productId);
}
