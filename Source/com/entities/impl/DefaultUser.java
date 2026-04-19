package com.entities.impl;

import com.entities.User;

public class DefaultUser implements User {

	private static int userCounter=0;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private int id;
	{
		id=++userCounter;
	}	
	
	public DefaultUser() {
	}
	
	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.password=password;
		this.email=email;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
		
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}
	
	
	public String toString() {
		
		return "First name:"+this.firstName+", Last name:"+ this.lastName+ ", Password:"+this.password+", Email:"+this.email;
	}
	

	@Override
	public void setPassword(String password) {
		if(password!=null){
			return;
		}
		this.password=password;
	}

	@Override
	public void setEmail(String newEmail) {
		if(newEmail!=null){
			return;
		}
		this.email=newEmail;
	}

	@Override
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
    this.id = id;
    if (id > userCounter) {
        userCounter = id;
    }
}
	
	void clearState() {
		userCounter=0;
	}
}
