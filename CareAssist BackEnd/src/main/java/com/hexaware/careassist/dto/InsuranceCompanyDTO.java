package com.hexaware.careassist.dto;


/*
@Author :  Sheshank Sharma
Modified Date : 31-01-2024
Description : DTO class for InsuranceCompanyDTO containing various properties
*/

public class InsuranceCompanyDTO {
	
	private long insuranceCompanyId;
	
	private String insuranceCompanyDescription;
	
    private String companyName;
    
    private String companyContactNumber;
    
    private String email;
    
    private String password;

	public InsuranceCompanyDTO() {
		super();
	}

	public InsuranceCompanyDTO(long insuranceCompanyId, String insuranceCompanyDescription, String companyName,
			String companyContactNumber, String email, String password) {
		super();
		this.insuranceCompanyId = insuranceCompanyId;
		this.insuranceCompanyDescription = insuranceCompanyDescription;
		this.companyName = companyName;
		this.companyContactNumber = companyContactNumber;
		this.email = email;
		this.password = password;
	}

	public long getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(long insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public String getInsuranceCompanyDescription() {
		return insuranceCompanyDescription;
	}

	public void setInsuranceCompanyDescription(String insuranceCompanyDescription) {
		this.insuranceCompanyDescription = insuranceCompanyDescription;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyContactNumber() {
		return companyContactNumber;
	}

	public void setCompanyContactNumber(String companyContactNumber) {
		this.companyContactNumber = companyContactNumber;
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
		return "InsuranceCompanyDTO [insuranceCompanyId=" + insuranceCompanyId + ", insuranceCompanyDescription="
				+ insuranceCompanyDescription + ", companyName=" + companyName + ", companyContactNumber="
				+ companyContactNumber + ", email=" + email + ", password=" + password + "]";
	}

    
    
}
