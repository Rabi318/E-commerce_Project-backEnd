package com.coder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coder.model.OrderItems;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long>{

}
