package com.entities.impl;

import java.lang.String;
import java.util.Arrays;

import com.entities.Order;
import com.entities.Product;

public class DefaultOrder implements Order {

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
	
	private String creditCardNumber;
	private Product[] products;
	private int customerId;

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		if(creditCardNumber.length()!=AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER || creditCardNumber==null){
			return false;
		}
		for(char c :creditCardNumber.toCharArray()){
			if(!Character.isDigit(c)){
				return false;
			}
		}
		return true;
}

	@Override
	public void setCreditCardNumber(String creditCardNumber) {
		if(isCreditCardNumberValid(creditCardNumber)){
			this.creditCardNumber=creditCardNumber;
		}
	}

	@Override
	public void setProducts(Product[] products) {
			this.products=products;
		
	}

	@Override
	public void setCustomerId(int customerId) {
		this.customerId=customerId;
	}


	@Override
	public int getCustomerId() {
		return this.customerId;
	}
	
	public String getCardNumber(){
		return this.creditCardNumber;
	}
	
	@Override
	public String toString() {
		return "Order: customer id - " + this.customerId + "\t" +
					"credit card number - " + this.creditCardNumber + "\t" + 
					"products - " + Arrays.toString(this.products);
	}

	
	
	

}
