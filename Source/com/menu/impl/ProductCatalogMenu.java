package com.menu.impl;

import java.util.Scanner;

import com.configs.ApplicationContext;
import com.entities.Cart;
import com.entities.Product;
import com.menu.Menu;
import com.services.ProductManagementService;
import com.services.impl.DefaultProductManagementService;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {
		Menu nexMenu=null;
		while(true){
			printMenuHeader();
			printProducts(productManagementService.getProducts());
			String userInput=getUserInput();
			if(context.getLoggedInUser()==null){
				System.out.println("User not logged in. Please, sign in or create new account");
				nexMenu=new MainMenu();
				break;
			}
			if(userInput.equalsIgnoreCase(CHECKOUT_COMMAND)){
				Cart cart = context.getSessionCart();
				if(cart==null||cart.isEmpty()){
					System.out.println("Cart is Empty, please add products to cart to checkout.");
				}
				else{
					nexMenu= new CheckoutMenu();
					break;
				}
			}
			if(userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)){
				nexMenu=new MainMenu();
				break;
			}
			
			else{
				Product product=getProduct(userInput);
				if(product==null){
					System.out.println("Product not found");
					continue;
				}
				addToCart(product);
			}

		}
		nexMenu.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*****PRODUCT CATALOG*****");
	}

	private String getUserInput(){
		Scanner scanner= new Scanner((System.in));
		System.out.println("Enter product id to add it to the cart or 'menu' if you want to navigate back to the main menu");
		String userInput= scanner.nextLine();
		
		return userInput;
		
	}
	private void printProducts(Product[] products){
		for(Product product:products){
			System.out.println(product.toString());
		}
	}
	private Product getProduct(String UserInput){
			int productId = Integer.parseInt(UserInput);
			Product product=productManagementService.getProductById(productId);
			return product;
	}
	private void addToCart(Product product){
		context.getSessionCart().addProduct(product);
		System.out.printf("Product %s has been added to your cart. "
				+ "If you want to add a new product - enter the product id. "
				+ "If you want to proceed with checkout - enter word "
				+ "'checkout' to console %n",product.getProductName());
	}
}
