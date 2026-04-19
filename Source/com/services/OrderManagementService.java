package com.services;

import com.entities.Order;

public interface OrderManagementService {

	void addOrder(Order order);

	Order[] getOrdersByUserId(int userId);
	
	Order[] getOrders();

}
