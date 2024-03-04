package com.hexaware.careassist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careassist.dto.AuthRequest;
import com.hexaware.careassist.dto.InsuranceCompanyDTO;
import com.hexaware.careassist.entities.InsuranceCompany;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchInsuranceCompanyFoundException;
import com.hexaware.careassist.service.IInsuranceCompanyService;
import com.hexaware.careassist.service.JwtService;

/*
@Author :  Sheshank Sharma
Modified Date : 05-02-2024
Description : Creation of InsuranceCompanyRestController
*/

@RestController
@RequestMapping("/api/v1/insurancecompany")
@CrossOrigin(origins="http://localhost:4200")
public class InsuranceCompanyRestController {
	
	@Autowired
	IInsuranceCompanyService insuranceCompanyService; 
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private Logger logger =LoggerFactory.getLogger(InsuranceCompanyRestController.class);
	
	
	
	@GetMapping("/get/{insuranceCompanyId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY') || hasAuthority('ADMIN')")
	public InsuranceCompanyDTO getInsuranceCompanyById(@PathVariable long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException {
		
		return insuranceCompanyService.getInsuranceCompanyById(insuranceCompanyId);
	}
	
	@GetMapping("/getByEmail/{email}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')")
	public InsuranceCompanyDTO getInsuranceCompanyByEmail(@PathVariable String email) throws NoSuchInsuranceCompanyFoundException {
		
		return insuranceCompanyService.getInsuranceCompanyByEmail(email);
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY') || hasAuthority('ADMIN')")
	public InsuranceCompany updateInsuranceCompany(@RequestBody InsuranceCompanyDTO insuranceCompanyDto) throws NoSuchInsuranceCompanyFoundException, EmailAlreadyPresentException {
		
		return insuranceCompanyService.updateInsuranceCompany(insuranceCompanyDto);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<InsuranceCompany> getAllInsuranceCompany(){
		return insuranceCompanyService.getAllInsuranceCompany();
	}

	@DeleteMapping("/delete/{insuranceCompanyId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public boolean deleteInsuranceCompanyById(@PathVariable long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException {
		
		return insuranceCompanyService.deleteInsuranceCompanyById(insuranceCompanyId);
	}
	
	@PostMapping("/register")
	public InsuranceCompany addInsuranceCompany(@RequestBody InsuranceCompanyDTO insuranceCompanyDto) throws EmailAlreadyPresentException {
		
		return insuranceCompanyService.addInsuranceCompany(insuranceCompanyDto);
	}
	
	
	@GetMapping("/getByName/{insuranceCompanyName}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY') || hasAuthority('ADMIN')")
	public List<InsuranceCompany> getInsuranceCompanyByName(@PathVariable String insuranceCompanyName){
		
		return insuranceCompanyService.getInsuranceCompanyByName(insuranceCompanyName);
	}
	
	@PostMapping("/login")
	public String authenticateAndGenerateToken(@RequestBody AuthRequest authReq) {

		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));

		
		String token = null;
		if (authenticate.isAuthenticated()) {
			token = jwtService.generateToken(authReq.getEmail(),"INSURANCE_COMPANY");
			logger.info("JWT Token successfully generated!!!");
		}

		else {
			logger.info("EMAIL Not Found!!!!");
			throw new UsernameNotFoundException("EMAIL Not Found!!!! ");
		}
		return token;

	}
}
