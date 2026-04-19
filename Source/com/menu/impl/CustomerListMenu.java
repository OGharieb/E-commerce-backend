package com.menu.impl;

import com.entities.User;
import com.menu.Menu;
import com.services.UserManagementService;
import com.services.impl.DefaultUserManagementService;

public class CustomerListMenu implements Menu {

	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		
	}
	
	@Override
	public void start() {
		printMenuHeader();
		User[] users=userManagementService.getUsers();
		if(users.length==0){
			System.out.println("Unfortunately, no customers exist.");
		}
		else{
		for(User user:users){
			System.out.println("First name:"+user.getFirstName()+", Last name:"+ user.getLastName()+ ", Email:"+user.getEmail());
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** CUSTOMER LIST ***")	;
	}

}
