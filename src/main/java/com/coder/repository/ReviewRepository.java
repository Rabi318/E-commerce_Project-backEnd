package com.coder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coder.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
