package com.menu.impl;

import com.configs.ApplicationContext;
import com.menu.Menu;

public class SignOutMenu implements Menu {

	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		context.setLoggedInUser(null);
		printMenuHeader();
	}

	@Override
	public void printMenuHeader() {
	
		System.out.println("*****SIGN OUT*****");
		System.out.println("Have a nice day! Look forward to welcoming back!");		
	}
}
