package com.entities.impl;

import java.util.Arrays;

import com.entities.Cart;
import com.entities.Product;

public class DefaultCart implements Cart {
	private final int DEFAULT_PRODUCTS_CAPACITY=10;
	Product[] products;
	{
		products= new Product[DEFAULT_PRODUCTS_CAPACITY];
	}
	private int lastProductIndex=0;
	
	@Override
	public boolean isEmpty() {
		if(products==null||products.length==0){
			return true;
		}
		for(Product product : products){
			if(product!=null){
				return false;
			}
		}
		return true;
	}

	@Override
	public void addProduct(Product product) {
		if(product==null){
			return;
		}
		if(lastProductIndex>=products.length){
			products=Arrays.copyOf(products, products.length<<1);
		}
		products[lastProductIndex++]=product;
	}

	@Override
	public Product[] getProducts() {
		int nonNullCount=0,index=0;

		for(Product product:products){
			if(product!=null){
				nonNullCount++;
			}
		}
		Product[] nonNullProducts=new Product[nonNullCount];
		for(Product product:products){
			if(product!=null){
				nonNullProducts[index++]=product;
		}
	}
		return nonNullProducts;
	}

	@Override
	public void clear() {
		products= new Product[DEFAULT_PRODUCTS_CAPACITY];
		lastProductIndex=0;
	}

}
