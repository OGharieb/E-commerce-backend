	package com.menu.impl;

	import java.util.Scanner;

	import com.configs.ApplicationContext;
	import com.menu.Menu;

	public class SettingsMenu implements Menu {

		private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
				+ "2. Change Email";

		private ApplicationContext context;

		{
			context = ApplicationContext.getInstance();
		}

		@Override
		public void start() {
			Menu nextMenu=null;
			mainLoop: while(true){
				if(context.getLoggedInUser()==null){
					nextMenu=new MainMenu();
					System.out.println("User not logged in. Please, sign in or create new account");
					break mainLoop;
				}
				else{
					System.out.println(SETTINGS);
					String userInput=getUserInput();
					if(userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)){
					nextMenu= new MainMenu();
					break mainLoop;
					}
					int userOption= Integer.parseInt(userInput);
					switch (userOption) {
						case 1:
							nextMenu=new ChangePasswordMenu();
							break mainLoop;
						case 2:
							nextMenu=new ChangeEmailMenu();
							break mainLoop;
						default:
							System.out.println("Only 1, 2 is allowed. Try one more time");
							continue;
						}
					}
				}
			nextMenu.start();
		}

		@Override
		public void printMenuHeader() {
			System.out.println("*** SETTINGS ***");
		}
		private String getUserInput(){
			Scanner scanner=new Scanner(System.in);
			System.out.println("Please, enter option or type 'menu' to navigate back to the main menu: ");
			String UserInput=scanner.nextLine();
			return UserInput;
		}

	}
