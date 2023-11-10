package com.coder.service;

import com.coder.model.OrderItems;
import com.coder.repository.OrderItemRepository;
import com.coder.repository.OrderRepository;

public class OrderItemServiceImplementation implements OrderItemService{

	private OrderItemRepository orderItemRepository;
	
	
	@Override
	public OrderItems createOrderItems(OrderItems orderItems) {
		
		
		return orderItemRepository.save(orderItems);
	}

}
