package com.coder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coder.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
