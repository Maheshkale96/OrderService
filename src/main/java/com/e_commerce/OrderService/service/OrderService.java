package com.e_commerce.OrderService.service;

import com.e_commerce.OrderService.model.OrderRequest;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

}
