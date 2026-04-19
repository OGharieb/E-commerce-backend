package com.menu.impl;

import java.util.Scanner;

import com.configs.ApplicationContext;
import com.menu.Menu;

public class ChangePasswordMenu implements Menu {
	
	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter new password");
		String password=scanner.nextLine();
		context.getLoggedInUser().setPassword(password);
		System.out.println("password successfully changed");
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*****CHANGE PASSWORD*****");
	}

}
