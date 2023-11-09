package com.coder.service;

import org.springframework.stereotype.Service;

import com.coder.exception.ProductException;
import com.coder.model.Cart;
import com.coder.model.User;
import com.coder.repository.CartRepository;
import com.coder.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{

	private CartRepository cartRepository;
	
	private CartItemService cartItemService;
	
	@Override
	public Cart createCart(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findUserCart(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
