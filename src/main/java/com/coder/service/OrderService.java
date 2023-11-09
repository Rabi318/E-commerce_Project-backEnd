package com.coder.service;

import com.coder.model.Address;
import com.coder.model.Order;
import com.coder.model.User;

public interface OrderService {

	public Order createOrder(User user, Address shippingAdress);
	
}
