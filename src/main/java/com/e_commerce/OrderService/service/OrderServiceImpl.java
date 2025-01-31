package com.e_commerce.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.OrderService.entity.Order;
import com.e_commerce.OrderService.external.client.ProductService;
import com.e_commerce.OrderService.model.OrderRequest;
import com.e_commerce.OrderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public long placeOrder(OrderRequest orderRequest) {
		
		
		log.info("Placing Order Request: {} ", orderRequest);
		
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
		log.info("Creating Order with Status CREATED");
		
		Order order = Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus("CREATED")
				.productId(orderRequest.getProductId())
				.orderDate(Instant.now())
				.quantity(orderRequest.getQuantity())
				.build();
		
		order = orderRepository.save(order);
		
		log.info("Order Places successfully with Order Id: {}", order.getId());
		
		
		// TODO Auto-generated method stub
		return order.getId();
	}

}
