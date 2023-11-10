package com.coder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coder.exception.OrderException;
import com.coder.model.Address;
import com.coder.model.Cart;
import com.coder.model.CartItem;
import com.coder.model.Order;
import com.coder.model.OrderItems;
import com.coder.model.User;
import com.coder.repository.AddressRepository;
import com.coder.repository.CartRepository;
import com.coder.repository.OrderItemRepository;
import com.coder.repository.OrderRepository;
import com.coder.repository.UserRepository;
import com.coder.user.domain.OrderStatus;
import com.coder.user.domain.PaymentStatus;

@Service
public class OrderServiceImplementation implements OrderService{

	
	
	private OrderRepository orderRepository;
	private CartService cartService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	
	private OrderItemRepository orderItemRepository;
	
	
	
	
	public OrderServiceImplementation(OrderRepository orderRepository, CartService cartService,
			AddressRepository addressRepository, UserRepository userRepository, 
			OrderItemRepository orderItemRepository) {
		super();
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public Order createOrder(User user, Address shippingAdress) {
		
		shippingAdress.setUser(user);
		Address address = addressRepository.save(shippingAdress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItems> orderItems = new ArrayList<>();
		
		for(CartItem item: cart.getCartItems()) {
			OrderItems orderItem = new OrderItems();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItems createdOrderItem = orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItem);
		}
		
		Order createOrder = new Order();
		createOrder.setUser(user);
		createOrder.setOrderItems(orderItems);
		createOrder.setToatalPrice(cart.getTotalPrice());
		createOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createOrder.setDiscounte(cart.getDiscounte());
		createOrder.setTotalItem(cart.getTotalItem());
		
		Order savedOrder = orderRepository.save(createOrder);
		
		for(OrderItems item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		
		Optional<Order> opt = orderRepository.findById(orderId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("Order not exit with id "+orderId);
		
		
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		
		List<Order> orders = orderRepository.getUsersOrders(userId);
		
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		
		order.setOrderStatus(OrderStatus.PLACED);
		order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
		
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CONFIRMED);
		
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.SHIPPED);
		
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.DELIVERED);
		
		return orderRepository.save(order);
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CANCELLED);
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
		
	}
	
	

}
