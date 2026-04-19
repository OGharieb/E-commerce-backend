package com.menu.impl;

import java.util.Scanner;
import com.configs.ApplicationContext;
import com.entities.User;
import com.entities.impl.DefaultUser;
import com.menu.Menu;
import com.services.UserManagementService;
import com.services.impl.DefaultUserManagementService;

public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;
	private ApplicationContext context;
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter first name:");
		String firstName=scanner.next();
		System.out.println("Enter last name:");
		String lastName = scanner.next();
		scanner.nextLine();
		System.out.println("Enter mail:");	
		String email = scanner.nextLine();
		System.out.println("Enter Password:");
		String password=scanner.nextLine();
		User user=new DefaultUser(firstName,lastName,password,email);
		String errorMessage=userManagementService.registerUser(user);
		if(errorMessage==null||(errorMessage.isEmpty())){
			context.setLoggedInUser(user);
			System.out.println("New user created");
		}
		else{
			System.out.println(errorMessage);
		}
		
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SIGN UP *****");
	}

}

