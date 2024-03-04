package com.hexaware.careassist.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 31-01-2024
Description : Entity class for Admin containing various properties
*/

@Entity
public class Admin {
	@Id
	@SequenceGenerator(name="adminId_generator",sequenceName="admin_seq",initialValue = 1001)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="adminId_generator")
	private long adminId;
	
	@Pattern(regexp="^[a-zA-Z ]{1,20}$", message="Invalid name provided; should have only alphabets with a maximum length of 20")
	private String adminName;
	
	@Email
	private String email;
	
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$",message="password must have at least 1 upper case, 1 lower case,1 special character, 1 digit and must be of minimum leangth 8")
	private String password;
	
	private final String ROLE="ADMIN";

	public Admin() {
		super();
	}

	public Admin(long adminId,
			@Pattern(regexp="^[a-zA-Z ]{1,20}$", message="Invalid name provided; should have only alphabets with a maximum length of 20") String adminName,
			@Email String email,
			@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$",message="password must have at least 1 upper case, 1 lower case,1 special character, 1 digit and must be of minimum leangth 8")
	String password) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.email = email;
		this.password = password;
	}


	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", password=" + password
				+ "]";
	}

	public String getRole() {
		return this.ROLE;
	}

	
}
