package com.menu.impl;

import java.util.Scanner;

import com.configs.ApplicationContext;
import com.menu.Menu;

public class ChangeEmailMenu implements Menu {
	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter new email");
		String email=scanner.nextLine();
		context.getLoggedInUser().setEmail(email);
		System.out.println("email successfully changed");
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*****CHANGE EMAIL*****");
	}

}
