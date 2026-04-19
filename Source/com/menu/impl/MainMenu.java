package com.menu.impl;

import java.util.Scanner;

import com.Main;
import com.configs.ApplicationContext;
import com.menu.Menu;

public class MainMenu implements Menu {

	public static final String MENU_COMMAND = "menu";
	
	private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
			+ "1. Sign Up" + System.lineSeparator() + "2. Sign In"
			+ System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
			+ "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() + 
			"6. Customer List";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
			+ "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
			+ System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
			+ "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() + 
			"6. Customer List";

	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		while(true){
			if(ApplicationContext.getInstance()==null){
				context.setMainMenu(this);
			}
			Menu nextMenu= null;
			mainLoop : while(true){
				Scanner scanner=new Scanner(System.in);
				printMenuHeader();
				System.out.println("User Input:");
				String userInput=scanner.next();
				if(userInput.equalsIgnoreCase(Main.EXIT_COMMAND)){
					scanner.close();
					System.exit(0);
				}
				else{
					int choiceNum=Integer.parseInt(userInput);
					switch (choiceNum) {
						case 1:
							nextMenu=new SignUpMenu();
							break mainLoop;
						case 2:
							if(context.getLoggedInUser()!=null){
								nextMenu=new SignOutMenu();
							}
							else{
								nextMenu= new SignInMenu();
							}
							break mainLoop;
						case 3: 
							nextMenu=new ProductCatalogMenu();
							break mainLoop;
						case 4:
							nextMenu=new MyOrdersMenu();
							break mainLoop;
						case 5:
							nextMenu=new SettingsMenu();
							break mainLoop;
						case 6: 
							nextMenu=new CustomerListMenu();
							break mainLoop;
					
						default:
							System.out.println("only choices from 1-6 allowed, please try again");
							continue;
					}
				}
			}
		nextMenu.start();
		}
		
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*******Main Menu*******");
		if(context.getLoggedInUser()==null){
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
		}
		else{
			System.out.print(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
		}
	}

}