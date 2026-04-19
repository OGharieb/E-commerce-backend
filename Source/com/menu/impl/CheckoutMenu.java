package com.menu.impl;

import java.util.Scanner;

import com.configs.ApplicationContext;
import com.entities.Order;
import com.entities.impl.DefaultOrder;
import com.menu.Menu;
import com.services.OrderManagementService;
import com.services.impl.DefaultOrderManagementService;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;
	
	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}
	
	@Override
	public void start() {
		while(true){
			printMenuHeader();
			System.out.println("Enter your credit card number without spaces and press enter if you confirm purchase");
			if(!(createOrder(getCreditCardNum()))){
				System.out.println("Credit card number invalid, please try again");
				continue;
			}
			else{
				System.out.println("Order successfully created.");
				context.getSessionCart().clear();
				break;
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** CHECKOUT ***");
	}
	private String getCreditCardNum(){
		Scanner scanner = new Scanner(System.in);
		String creditNum=scanner.nextLine();
		return creditNum;
	}
	private boolean createOrder(String CreditNum){
		Order order=new DefaultOrder();
		if(!(order.isCreditCardNumberValid(CreditNum))){
			return false;
		}
		else{
			order.setCreditCardNumber(CreditNum);
			order.setProducts(context.getSessionCart().getProducts());
			order.setCustomerId(context.getLoggedInUser().getId());
			orderManagementService.addOrder(order);
			return true;
		}
	}

}
