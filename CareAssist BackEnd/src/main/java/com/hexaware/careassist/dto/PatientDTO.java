package com.hexaware.careassist.dto;

import java.time.LocalDate;

/*
@Author :  Yash Dubey
Modified Date : 31-01-2024
Description : DTO class for PatientDTO containing various properties
*/

public class PatientDTO {
	
	private long patientId;
	
	
	private LocalDate dob;
	
	
    private String contact;
	
	
    private String address;
	
	
    private String patientName;
	
	
    private String patientGender;
	
	
    private String descriptionOfTreatment;
	
	
    private String email;
	
	private String password;
	
    
   
	public PatientDTO() {
		super();
	}

	public PatientDTO(long patientId, LocalDate dob, String contact, String address, String patientName,
			String patientGender, String descriptionOfTreatment, String email, String password) {
		super();
		this.patientId = patientId;
		this.dob = dob;
		this.contact = contact;
		this.address = address;
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.descriptionOfTreatment = descriptionOfTreatment;
		this.email = email;
		this.password = password;
	}


	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getDescriptionOfTreatment() {
		return descriptionOfTreatment;
	}

	public void setDescriptionOfTreatment(String descriptionOfTreatment) {
		this.descriptionOfTreatment = descriptionOfTreatment;
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
		return "Patient [patientId=" + patientId + ", dob=" + dob + ", contact=" + contact + ", address=" + address
				+ ", patientName=" + patientName + ", patientGender=" + patientGender + ", descriptionOfTreatment="
				+ descriptionOfTreatment + ", email=" + email + ", password=" + password + "]";
	}


	
    
   
    
}
