package com.coder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coder.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{

	@Query("SELECT r From Rating r Where r.product.is=:productId")
	public List<Rating> getAllProductsRating(@Param("productId")Long productId);
	
}
