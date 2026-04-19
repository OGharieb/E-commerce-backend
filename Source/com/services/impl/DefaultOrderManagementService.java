package com.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import com.entities.Order;
import com.entities.impl.DefaultOrder;
import com.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {
	

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;

	private Order[] orders= new Order[DEFAULT_ORDER_CAPACITY];
	
	private int lastOrderIndex;
	private DefaultOrderManagementService(){
		loadOrdersFromCsv();
	}
	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if(order==null){
			return;}
		if(lastOrderIndex>=orders.length){
			orders=Arrays.copyOf(orders, orders.length<<1);
		}
		orders[lastOrderIndex++]=order;
		saveOrderToCsv(order);
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		int amountOfOrders=0;
		for(Order order :orders){
			if(order!=null&& order.getCustomerId()==userId){
				amountOfOrders++;
			}
		}
			Order[] userOrders=new Order[amountOfOrders];
			int index=0;
			for(Order order :orders){
			if(order!=null&&order.getCustomerId()==userId){
				userOrders[index++]=order;
			}
		}
		return userOrders;
	
}

	@Override
	public Order[] getOrders() {
		int nonNullCount=0,index=0;
		for(Order order:orders){
			if(order!=null){
				nonNullCount++;
			}
		}
		Order[] nonNullOrders= new Order[nonNullCount];
		for(Order order:orders){
			if(order!=null){
				nonNullOrders[index++]=order;
			}
		}	
		
		return nonNullOrders;
	}
	
	void clearServiceState() {
		lastOrderIndex=0;
		orders=new Order[DEFAULT_ORDER_CAPACITY];
	}
	private void saveOrderToCsv(Order order) {
    File dir = new File("orders");
    if (!dir.exists()) {
        dir.mkdir();
    }
    String filePath = "orders" + File.separator + "orders" + order.getCustomerId() + ".csv";
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
        writer.println(order.getCustomerId() + "|" + order.getCardNumber());
    } catch (IOException e) {
        System.out.println("Couldn't write order");
    }
}

private void loadOrdersFromCsv() {
    File folder = new File("orders");
    if (!folder.exists()) {
        return;
    }
    File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));
    if (files == null) {
        return;
    }
    for (File file : files) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 2) {
                    Order loadedOrder = new DefaultOrder();
                    loadedOrder.setCustomerId(Integer.parseInt(data[0]));
                    loadedOrder.setCreditCardNumber(data[1]);
                    this.orders[lastOrderIndex++] = loadedOrder;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading order file");
        }
    }
}

}
