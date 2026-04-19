package com.services.impl;

import com.entities.User;
import com.entities.impl.DefaultUser;
import com.services.UserManagementService;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;




public class DefaultUserManagementService implements UserManagementService {

	private static final String USERS_DIR = "users";
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	
	private static final int DEFAULT_USERS_CAPACITY = 10;
	
	private static DefaultUserManagementService instance;
	private User[] users ;
	private  int lastUserIndex;

	private DefaultUserManagementService() {
		this.users=new User[100];
		this.lastUserIndex=0;
		loadFromCsv();
	}
	
	@Override
	public String registerUser(User user) {
		if(user==null){
			return NO_ERROR_MESSAGE;
		}
		if(checkUniqueEmail(user.getEmail())!=null&&!checkUniqueEmail(user.getEmail()).isEmpty()){
			return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
		}
		if(lastUserIndex>=users.length)
			users=Arrays.copyOf(users,users.length<<1);

		users[lastUserIndex]=user;
		lastUserIndex++;
		saveToCsv(user);
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	private String checkUniqueEmail(String email){
		if(email==null||email.isEmpty()){
			return EMPTY_EMAIL_ERROR_MESSAGE;
		}
		for(User user : users){
			if(user!=null&&user.getEmail()!=null && user.getEmail().equalsIgnoreCase(email)){
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			}

		}
		return NO_ERROR_MESSAGE;
	}

	
	@Override
	public User[] getUsers() {
		int nullUsersAmount=0;
		for(User user:users){
			if(user!=null)
				nullUsersAmount++;		
		}
		User[] nullUsers;
		nullUsers=new User[nullUsersAmount];
		int i=0;
		for(User user:users){
			if(user!=null){
				nullUsers[i++]=user;
			}
		}
		return nullUsers;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		for(User user:getUsers()){
			if(user.getEmail().equalsIgnoreCase(userEmail)){
				return user;
			}
		}
		return null;
	}
	
	void clearServiceState() {
		lastUserIndex=0;
		users=new User[DEFAULT_USERS_CAPACITY];
	}

	private void saveToCsv(User user){
		File directory = new File(USERS_DIR);
    if (!directory.exists()) {
        directory.mkdir();
    }
		String filePath = USERS_DIR + File.separator + user.getFirstName() + user.getLastName() + user.getId() + ".csv";
		try(PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))){
			writer.println(user.getId() + "|" + 
                    user.getFirstName() + "|" + 
                    user.getLastName() + "|" + 
                    user.getPassword() + "|" + 
                    user.getEmail());
		}
		catch(IOException e){
			System.out.println("Couldn't write into file");
		}
	}
	private void loadFromCsv(){
			File folder = new File(USERS_DIR);
			if(!folder.exists()||!folder.isDirectory()){
				return;
			}
			File[] listOfFiles= folder.listFiles((dir,name)->name.endsWith(".csv"));
			if(listOfFiles==null||listOfFiles.length==0){
				return;
			}
			

		for (File file : listOfFiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {
					
					String[] userData = line.split("\\|");

					if (userData.length == 5) {
						User loadedUser = new DefaultUser(userData[1], userData[2], userData[3], userData[4]);
						((DefaultUser) loadedUser).setId(Integer.parseInt(userData[0]));
						this.users[lastUserIndex++] = loadedUser;
					}
				}
			} catch (IOException e) {
				System.out.println("Hardware Error: Couldn't read file " + file.getName());
			} catch (NumberFormatException e) {
				System.out.println("Data Error: Corrupted ID in file " + file.getName());
			}
		}
	}
}

