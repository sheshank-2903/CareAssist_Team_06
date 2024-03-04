package com.hexaware.careassist.dto;


/*
@Author :  Sheshank Sharma
Modified Date : 31-01-2024
Description : DTO class for ClaimsDTO containing various properties
*/

public class ClaimsDTO {
	
	private long claimId;
	
	private double claimAmount;
	
	private String claimStatus;
	
	
	
	public ClaimsDTO() {
		super();
	}

	public ClaimsDTO(long claimId, double claimAmount, String claimStatus) {
		super();
		this.claimId = claimId;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
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

	@Override
	public String toString() {
		return "Claims [claimId=" + claimId + ", claimAmount=" + claimAmount + ", claimStatus=" + claimStatus
				+ "]";
	}
	
	
}
