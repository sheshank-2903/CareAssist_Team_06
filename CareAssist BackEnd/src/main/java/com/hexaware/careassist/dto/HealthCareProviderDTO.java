package com.hexaware.careassist.dto;

/*
@Author :  Sheshank Sharma
Modified Date : 31-01-2024
Description : DTO class for HealthCareProviderDTO containing various properties
*/

public class HealthCareProviderDTO {
	
	private long healthCareProviderId;
	
	private String healthCareProviderName;
	
    private String providerGender;
	
    private String address;
	
    private String email;
	
	private String password;

	public HealthCareProviderDTO() {
		super();
	}

	public HealthCareProviderDTO(long healthCareId, String healthCareProviderName,
			String providerGender, String address, String email, String password) {
		super();
		this.healthCareProviderId = healthCareId;
		this.healthCareProviderName = healthCareProviderName;
		this.providerGender = providerGender;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public long getHealthCareProviderId() {
		return healthCareProviderId;
	}

	public void setHealthCareProviderId(long healthCareProviderId) {
		this.healthCareProviderId = healthCareProviderId;
	}

	public String getHealthCareProviderName() {
		return healthCareProviderName;
	}

	public void setHealthCareProviderName(String healthCareProviderName) {
		this.healthCareProviderName = healthCareProviderName;
	}

	public String getProviderGender() {
		return providerGender;
	}

	public void setProviderGender(String providerGender) {
		this.providerGender = providerGender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "HealthCareProvider [healthCareProviderId=" + healthCareProviderId + ", healthcareProviderName=" + healthCareProviderName
				+ ", providerGender=" + providerGender + ", address=" + address + ", email=" + email + ", password="
				+ password + "]";
	} 
	
    
   
    
}
