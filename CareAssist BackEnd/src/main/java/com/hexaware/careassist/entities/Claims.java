package com.hexaware.careassist.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

/*
@Author :  Sheshank Sharma
Modified Date : 31-01-2024
Description : Entity class for Claims containing various properties
*/

@Entity
public class Claims {
	@Id
	@SequenceGenerator(name="claimId_generator",sequenceName="claim_seq",initialValue = 2001)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="claimId_generator")
	private long claimId;
	
	@Min(10000)
	private double claimAmount;
	
	@Pattern(regexp = "APPROVED|REJECTED|PENDING" , message="Invalid Status Provided can only be APPROVED|REJECTED|PENDING")
	private String claimStatus;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="patientId")
	private Patient patient;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="planId")
	private Plans plans;
	
	
	
	public Claims() {
		super();
	}

	public Claims(long claimId, @Min(10000) double claimAmount,
			@Pattern(regexp = "APPROVED|REJECTED|PENDING", message = "Invalid Status Provided can only be APPROVED|REJECTED|PENDING") String claimStatus,
			Patient patient, Plans plans) {
		super();
		this.claimId = claimId;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
		this.patient = patient;
		this.plans = plans;
	}


	public long getClaimId() {
		return claimId;
	}



	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}



	public double getClaimAmount() {
		return claimAmount;
	}



	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}



	public String getClaimStatus() {
		return claimStatus;
	}



	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}



	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	public Plans getPlans() {
		return plans;
	}



	public void setPlans(Plans plans) {
		this.plans = plans;
	}



	@Override
	public String toString() {
		return "Claims [claimId=" + claimId + ", claimAmount=" + claimAmount + ", claimStatus=" + claimStatus
				+  ", patient=" + patient + ", plans=" + plans + "]";
	}

	
	
	
	
	
}
