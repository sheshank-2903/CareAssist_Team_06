package com.hexaware.careassist.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
@Author :  Yash Dubey
Modified Date : 31-01-2024
Description : Entity class for Plans containing various properties
*/

@Entity
public class Plans {  
	@Id
	@SequenceGenerator(name="plansId_generator",sequenceName="plans_seq",initialValue = 6001)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="plansId_generator")
	private long planId;
	
	@Pattern(regexp="^[a-zA-Z ]{1,20}$", message="Invalid name provided; should have only alphabets with a maximum length of 20")
	private String planName;
	@NotBlank
	private String description; 
	
	@NotNull
    private LocalDate dateOfIssue;
	
    @NotNull
    @Min(10000)
    private double coverageAmount;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="plans")
    @JsonBackReference
    private Set<Claims> claimSet=new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name="insuranceCompanyId")
    @JsonBackReference
    private InsuranceCompany insuranceCompany;
    
    @ManyToMany(mappedBy = "insurancePlans")
    @JsonBackReference
    private Set<Patient> patients = new HashSet<>();

	public Plans() {
		super();
	}

	

	


	public Plans(long planId,
			@Pattern(regexp = "^[a-zA-Z ]{1,20}$", message = "Invalid name provided; should have only alphabets with a maximum length of 20") String planName,
			@NotBlank String description, @NotNull LocalDate dateOfIssue, @NotNull @Min(10000) double coverageAmount,
			Set<Claims> claimSet, InsuranceCompany insuranceCompany, Set<Patient> patients) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.description = description;
		this.dateOfIssue = dateOfIssue;
		this.coverageAmount = coverageAmount;
		this.claimSet = claimSet;
		this.insuranceCompany = insuranceCompany;
		this.patients = patients;
	}






	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public double getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public Set<Claims> getClaimSet() {
		return claimSet;
	}

	public void setClaimSet(Set<Claims> claimSet) {
		this.claimSet = claimSet;
	}

	public InsuranceCompany getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	
	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}






	@Override
	public String toString() {
		return "Plans [planId=" + planId + ", planName=" + planName + ", Description=" + description + ", dateOfIssue="
				+ dateOfIssue + ", coverage_amount=" + coverageAmount + ", claimSet=" + claimSet
				+ ", insuranceCompany=" + insuranceCompany + "]";
	}
	
   
}
