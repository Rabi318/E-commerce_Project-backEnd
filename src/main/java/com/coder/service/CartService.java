package com.coder.service;

import com.coder.exception.ProductException;
import com.coder.model.Cart;
import com.coder.model.User;
import com.coder.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
	
	
}
