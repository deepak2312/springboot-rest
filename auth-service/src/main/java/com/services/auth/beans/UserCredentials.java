package com.services.auth.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_auth_data")
public class UserCredentials {

	@Id
	private String userId;
	
	private String username;
	private String password;
	
	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
