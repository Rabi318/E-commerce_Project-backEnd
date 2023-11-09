package com.coder.service;

import java.util.List;

import com.coder.exception.ProductException;
import com.coder.model.Rating;
import com.coder.model.User;
import com.coder.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req, User user)throws ProductException;

	public List<Rating> getProductsRating(Long productId);
}
