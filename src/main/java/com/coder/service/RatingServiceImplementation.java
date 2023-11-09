package com.coder.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coder.exception.ProductException;
import com.coder.model.Product;
import com.coder.model.Rating;
import com.coder.model.User;
import com.coder.repository.RatingRepository;
import com.coder.request.RatingRequest;

@Service
public class RatingServiceImplementation  implements RatingService{
	
	private RatingRepository ratingRepository;
	private ProductService productService;
	
	public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService) {
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		
		Product product = productService.findProductById(req.getProductId());
		
		Rating rating = new Rating();  
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}

}
