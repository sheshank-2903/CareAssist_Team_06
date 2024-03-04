package com.hexaware.careassist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careassist.dto.PlansDTO;
import com.hexaware.careassist.entities.Plans;
import com.hexaware.careassist.exceptions.InvalidInputException;
import com.hexaware.careassist.exceptions.NoSuchInsuranceCompanyFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;
import com.hexaware.careassist.service.IPlansService;

/*
@Author :  Yash Dubey
Modified Date : 05-02-2024
Description : Creation of PlansRestController
*/

@RestController
@RequestMapping("/api/v1/plans")
@CrossOrigin(origins="http://localhost:4200")
public class PlansRestController {
	
	@Autowired
	IPlansService service;
	
	
	
	@PostMapping("/add/{insuranceCompanyId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')")
	public Plans addPlan(@RequestBody PlansDTO plansDto,@PathVariable long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException {		
		return service.addPlan(plansDto, insuranceCompanyId);
	}
	
	
	@PutMapping("/update/{planName}/{description}/{coverageAmount}/{planId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')")
	public Plans updatePlan(@PathVariable String planName,@PathVariable String description,@PathVariable double coverageAmount,@PathVariable long planId) throws NoSuchPlanFoundException {	
		return service.updatePlan(planName, description, coverageAmount, planId);
	}
	
	
	
	@DeleteMapping("/delete/{planId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY') || hasAuthority('ADMIN')")
	public boolean deletePlanById(@PathVariable long planId) throws NoSuchPlanFoundException {
		return service.deletePlanById(planId);
	}
	
	
	
	@GetMapping("/getById/{planId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY') || hasAuthority('PATIENT') || hasAuthority('ADMIN')")
	public PlansDTO getPlanById(@PathVariable long planId) throws NoSuchPlanFoundException {
		return service.getPlanById(planId);
	}
	
	
	
	@GetMapping("/getAll")
	@PreAuthorize("hasAuthority('ADMIN')|| hasAuthority('PATIENT')")
	public List<Plans> getAllPlans(){
		return service.getAllPlans();
	}
	
	
	
	@GetMapping("/getByName/{planName}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY') || hasAuthority('PATIENT') || hasAuthority('ADMIN')")
	public List<Plans> getPlanByName(@PathVariable String planName){
		return service.getPlanByName(planName);
	}
	
	@GetMapping("/getByNameAndCompanyId/{planName}/{insuranceCompanyId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')")
	public List<Plans> getPlanByName(@PathVariable String planName,@PathVariable Long insuranceCompanyId){
		return service.getPlanByNameAndCompanyId(planName, insuranceCompanyId);
	}
	
	
	
	@GetMapping("/getByCompanyName/{companyName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Plans> getPlanByInsuranceCompanyName(@PathVariable String companyName) throws NoSuchInsuranceCompanyFoundException{
		return service.getPlanByInsuranceCompanyName(companyName);
	}
	
	@GetMapping("/getByCompanyId/{insuranceCompanyId}")
	@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('INSURANCE_COMPANY')")
	public List<Plans> getPlanByInsuranceCompanyId(@PathVariable long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException{
		return service.getPlanByInsuranceCompanyId(insuranceCompanyId);
	}
	
	
	@GetMapping("/getByCoverageAmountLessThan/{coverageAmount}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public List<Plans> getPlanByCoverageAmountLessthan(@PathVariable double coverageAmount) throws InvalidInputException{
		return service.getPlanyByAmountLessThan(coverageAmount);
	}
	
	
	
	@GetMapping("/getByPatientId/{patientId}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public List<Plans> getPlanyByPatientId(@PathVariable long patientId){
		return service.getPlanyByPatientId(patientId);
	}
}
