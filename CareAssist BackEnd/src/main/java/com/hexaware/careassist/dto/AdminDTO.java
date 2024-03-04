package com.hexaware.careassist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 31-01-2024
Description : DTO class for Admin containing various properties
*/

public class AdminDTO {

	private long adminId;

	private String adminName;

	private String email;
	
	private String password;

	public AdminDTO() {
		super();
	}

	public AdminDTO(long adminId, @NotBlank String adminName, @Email String email, String password) {
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
}
