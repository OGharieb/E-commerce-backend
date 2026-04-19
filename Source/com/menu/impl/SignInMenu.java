package com.menu.impl;

import java.util.Scanner;

import com.configs.ApplicationContext;
import com.entities.User;
import com.menu.Menu;
import com.services.UserManagementService;
import com.services.impl.DefaultUserManagementService;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		Scanner scanner=new Scanner(System.in);
		printMenuHeader();;
		System.out.println("Enter your email:");
		String email=scanner.nextLine();
		System.out.println("Enter your password:");
		String password=scanner.nextLine();
			User user=userManagementService.getUserByEmail(email);
			if(user!=null&&user.getPassword().equals(password)){
				context.setLoggedInUser(user);
				System.out.println("welcome back "+user.getFirstName()+" "+user.getLastName()+".");
			}
		else{	
			System.out.println("Email or password not found.");
			}
		}

	

	@Override
	public void printMenuHeader() {
		System.out.println("***** SIGN IN*****");
	}

}
