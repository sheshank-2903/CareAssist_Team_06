package com.hexaware.careassist.dto;

import java.time.LocalDate;

/*
@Author :  Yash Dubey
Modified Date : 31-01-2024
Description : DTO class for PlansDTO containing various properties
*/

public class PlansDTO {  
	
	private long planId;
	
	private String planName;
	
	private String description; 
	
	
    private LocalDate dateOfIssue;
    
    
    private double coverageAmount;
    

	public PlansDTO() {
		super();
	}


	public PlansDTO(long planId, String planName, String description, LocalDate dateOfIssue, double coverageAmount) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.description = description;
		this.dateOfIssue = dateOfIssue;
		this.coverageAmount = coverageAmount;
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


	@Override
	public String toString() {
		return "Plans [planId=" + planId + ", planName=" + planName + ", description=" + description + ", dateOfIssue="
				+ dateOfIssue + ", coverage_amount=" + coverageAmount + "]";
	}

	
   
}
