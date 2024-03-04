package com.hexaware.careassist.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
@Author :  Yash Dubey
Modified Date : 31-01-2024
Description : Entity class for Patient containing various properties
*/

@Entity
public class Patient {
	@Id
	@SequenceGenerator(name="patientId_generator",sequenceName="patient_seq",initialValue = 7001)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="patientId_generator")
	long patientId;
	
	@NotNull
	private LocalDate dob;
	
	@Pattern(regexp="[\\d]{10}",message="Please enter 10 digit number")
    private String contact;
	
	@NotBlank
    private String address;
	
	@Pattern(regexp="^[a-zA-Z ]{1,20}$", message="Invalid name provided; should have only alphabets with a maximum length of 20")
    private String patientName;
	
	@Pattern(regexp = "MALE|FEMALE" , message="Gender Provided can only be MALE|FEMALE")
    private String patientGender;
	
	@NotBlank
    private String descriptionOfTreatment;
	
	@Email
    private String email;
	
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$",message="password must have at least 1 upper case, 1 lower case,1 special character, 1 digit and must be of minimum leangth 8")
	private String password;
	
    @OneToMany(cascade=CascadeType.ALL,mappedBy="patient")
	 // patientId column will be created in Claims class table or we can say it is foreign key in department table
    @JsonBackReference
	private Set<Claims> claimSet=new HashSet<>(); // Collection should be initialised to avoid null pointer exception
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="patient")
    @JsonBackReference
    private Set<Invoices> invoiceSet=new HashSet<>();
    
    private final String ROLE="PATIENT";

    @ManyToMany
    @JsonBackReference
    @JoinTable(
        name = "patient_insurance_plan",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "insurance_plan_id")
    )
    private Set<Plans> insurancePlans = new HashSet<>();
    
	

	public Patient(long patientId, @NotNull LocalDate dob,
			@Pattern(regexp = "[\\d]{10}", message = "Please enter 10 digit number") String contact,
			@NotBlank String address,
			@Pattern(regexp = "^[a-zA-Z ]{1,20}$", message = "Invalid name provided; should have only alphabets with a maximum length of 20") String patientName,
			@Pattern(regexp = "MALE|FEMALE", message = "Gender Provided can only be MALE|FEMALE") String patientGender,
			@NotBlank String descriptionOfTreatment, @Email String email,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&./+]{8,}$", message = "password must have at least 1 upper case, 1 lower case,1 special character, 1 digit and must be of minimum leangth 8") String password,
			Set<Claims> claimSet, Set<Invoices> invoiceSet, Set<Plans> insurancePlans) {
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
		this.claimSet = claimSet;
		this.invoiceSet = invoiceSet;
		this.insurancePlans = insurancePlans;
	}


	public Patient() {
		super();
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


	public Set<Claims> getClaimSet() {
		return claimSet;
	}


	public void setClaimSet(Set<Claims> claimSet) {
		this.claimSet = claimSet;
	}


	public Set<Invoices> getInvoiceSet() {
		return invoiceSet;
	}


	public void setInvoiceSet(Set<Invoices> invoiceSet) {
		this.invoiceSet = invoiceSet;
	}


	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", dob=" + dob + ", contact=" + contact + ", address=" + address
				+ ", patientName=" + patientName + ", patientGender=" + patientGender + ", descriptionOfTreatment="
				+ descriptionOfTreatment + ", email=" + email + ", password=" + password + ", claimSet=" + claimSet
				+ ", invoiceSet=" + invoiceSet + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(patientId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return patientId == other.patientId;
	}
    
	
	public String getRole() {
		return this.ROLE;
	}


	public Set<Plans> getInsurancePlans() {
		return insurancePlans;
	}


	public void setInsurancePlans(Set<Plans> insurancePlans) {
		this.insurancePlans = insurancePlans;
	}
     
}
