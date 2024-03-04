package com.hexaware.careassist.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.careassist.entities.Patient;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 09-02-2024
Description : Creation of PatientInfoPatientDetails
*/

public class PatientInfoPatientDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String role; //change - 6
	private List<GrantedAuthority> authorities;
	
	
	public PatientInfoPatientDetails(Patient patient) {
		super();
		this.email = patient.getEmail();
		this.password = patient.getPassword();
		this.role=patient.getRole(); //change - 7
		this.authorities = Arrays.stream(patient.getRole().split(","))
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public String getRole() { // change - 8
		return role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
