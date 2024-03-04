package com.hexaware.careassist.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/*
@Author : Sheshank Sharma
Modified Date : 31-01-2024
Description : Entity class for InsuranceCompany containing various properties
*/

@Entity
public class InsuranceCompany {
	@Id
	@SequenceGenerator(name="insuranceCompanyId_generator",sequenceName="insuranceCompany_seq",initialValue = 4001)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="insuranceCompanyId_generator")
	private long insuranceCompanyId;
	@NotBlank
	private String insuranceCompanyDescription;
	@Pattern(regexp="^[a-zA-Z ]{1,20}$", message="Invalid name provided; should have only alphabets with a maximum length of 20")
    private String companyName;
    @Pattern(regexp="\\d{10}",message="Please enter 10 digit number")
    private String companyContactNumber;
    @Email
    private String email;
    
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$",message="password must have at least 1 upper case, 1 lower case,1 special character, 1 digit and must be of minimum leangth 8")
	private String password;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="insuranceCompany") 
    @JsonBackReference
	private Set<Plans> planSet=new HashSet<>();
    
    private final String ROLE="INSURANCE_COMPANY";

	public InsuranceCompany() {
		super();
	}

	public InsuranceCompany(long insuranceCompanyId, @NotBlank String insuranceCompanyDescription,
			@Pattern(regexp="^[a-zA-Z ]{1,20}$", message="Invalid name provided; should have only alphabets with a maximum length of 20") String companyName,
			@Pattern(regexp = "\\d{10}", message = "Please enter 10 digit number") String companyContactNumber,
			@Email String email,
			@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$",message="password must have at least 1 upper case, 1 lower case,1 special character, 1 digit and must be of minimum leangth 8") String password,
			Set<Plans> planSet) {
		super();
		this.insuranceCompanyId = insuranceCompanyId;
		this.insuranceCompanyDescription = insuranceCompanyDescription;
		this.companyName = companyName;
		this.companyContactNumber = companyContactNumber;
		this.email = email;
		this.password = password;
		this.planSet = planSet;
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

	public Set<Plans> getPlanSet() {
		return planSet;
	}

	public void setPlanSet(Set<Plans> planSet) {
		this.planSet = planSet;
	}

	@Override
	public String toString() {
		return "InsuranceCompany [insuranceCompanyId=" + insuranceCompanyId + ", insuranceCompanyDescription="
				+ insuranceCompanyDescription + ", companyName=" + companyName + ", companyContactNumber="
				+ companyContactNumber + ", email=" + email + ", password=" + password + ", planSet=" + planSet + "]";
	}
    
	public String getRole() {
		return this.ROLE;
	}
    
}
