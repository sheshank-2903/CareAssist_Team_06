package com.hexaware.careassist.dto;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 09-02-2024
Description : AuthRequest Class containing various properties
*/

public class AuthRequest {

	private String email;
	private String password;	
	
	public AuthRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public AuthRequest() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
