package com.coder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.exception.CartItemException;
import com.coder.exception.UserException;
import com.coder.model.CartItem;
import com.coder.model.User;
import com.coder.response.ApiResponse;
import com.coder.service.CartItemService;
import com.coder.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	private CartItemService cartItemService;
	private UserService userService;
	
	public CartItemController(CartItemService cartItemService,UserService userService) {
		// TODO Auto-generated constructor stub
		this.cartItemService = cartItemService;
		this.userService = userService;
	}
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt) throws CartItemException, UserException{
		User user = userService.findUserProfileByJwt(jwt);
		
		cartItemService.removeCartItem(user.getId(), cartItemId);
		 
		ApiResponse res = new ApiResponse("Item remove from cart", true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId, @RequestBody CartItem cartItem, @RequestHeader("Authorization")String jwt) throws UserException, CartItemException{
		
		User user = userService.findUserProfileByJwt(jwt);
		CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		return new ResponseEntity<>(updateCartItem, HttpStatus.ACCEPTED);
	}
	
}
