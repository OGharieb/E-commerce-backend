package com.menu.impl;

import com.configs.ApplicationContext;
import com.entities.Order;
import com.menu.Menu;
import com.services.OrderManagementService;
import com.services.impl.DefaultOrderManagementService;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		if(context.getLoggedInUser()==null){
			System.out.println("Please, log in or create new account to see list of your orders");
			new MainMenu().start();
		}
		else{
		printOrders();	
		}	
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** MY ORDERS ***");	
	}
	private void printOrders(){
		Order[] orders=orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());
		if(orders==null||orders.length==0){
			System.out.println("Unfortunately, you don't have any orders yet. Navigate back to main menu to place a new order");
		return;
		}
		else{
			for(Order order:orders){
				System.out.println(order.toString());
			}
		}
	}

}