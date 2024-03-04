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

import com.hexaware.careassist.dto.ClaimsDTO;
import com.hexaware.careassist.entities.Claims;
import com.hexaware.careassist.entities.Patient;
import com.hexaware.careassist.entities.Plans;
import com.hexaware.careassist.exceptions.InvoiceNotApprovedException;
import com.hexaware.careassist.exceptions.NoSuchClaimFoundException;
import com.hexaware.careassist.exceptions.NoSuchInsuranceCompanyFoundException;
import com.hexaware.careassist.exceptions.NoSuchInvoiceFoundException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;
import com.hexaware.careassist.service.IClaimsService;

/*
@Author :  Sheshank Sharma
Modified Date : 05-02-2024
Description : Creation of ClaimsRestController
*/

@RestController
@RequestMapping("/api/v1/claims")
@CrossOrigin(origins="http://localhost:4200")
public class ClaimsRestController {
	
	@Autowired
	IClaimsService claimService;

	
	
	@PostMapping("/add/{patientId}/{planId}/{invoiceId}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public Claims addClaim(@RequestBody ClaimsDTO claimDto,@PathVariable long patientId,@PathVariable long planId,@PathVariable long invoiceId) throws NoSuchPatientFoundException, NoSuchPlanFoundException, NoSuchInvoiceFoundException, InvoiceNotApprovedException {
		return claimService.addClaim(claimDto, patientId, planId,invoiceId);
	}
	
	@PutMapping("/update/{claimId}/{newStatus}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')")
	public Claims updateClaim(@PathVariable String newStatus,@PathVariable long claimId) throws NoSuchClaimFoundException {
		return claimService.updateClaim(newStatus, claimId);
	}
	
	@GetMapping("/get/{claimId}")
	@PreAuthorize("hasAuthority('PATIENT') || hasAuthority('ADMIN') || hasAuthority('INSURANCE_COMPANY')")
	public ClaimsDTO getClaimById(@PathVariable long claimId) throws NoSuchClaimFoundException {
		return claimService.getClaimById(claimId);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Claims> getAllClaims() {
		return claimService.getAllClaims();
	}
	
	@DeleteMapping("/delete/{claimId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public boolean deleteClaimById(@PathVariable long claimId) throws NoSuchClaimFoundException {
		return claimService.deleteClaimById(claimId);
	}
	
	@GetMapping("/getByPatientId/{patientId}")
	@PreAuthorize("hasAuthority('PATIENT') || hasAuthority('ADMIN')|| hasAuthority('INSURANCE_COMPANY')")
	public List<Claims> getClaimsByPatientId(@PathVariable long patientId) {
		return claimService.getClaimsByPatientId(patientId);
	}
	
	@GetMapping("/getByStatus/{status}/{patientId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY') || hasAuthority('PATIENT')")
	public List<Claims> getClaimsByStatus(@PathVariable String status,@PathVariable long patientId) {
		return claimService.getClaimsByStatus(status,patientId);
	}
	
	@GetMapping("/getByPlanId/{planId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')")
	public List<Claims> getClaimsByPlanId(@PathVariable long planId) {
		return claimService.getClaimsByPlanId(planId);
	}
	
	@GetMapping("/getByCompanyId/{insuranceCompanyId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')")
	public List<Claims> getClaimsByCompanyId(@PathVariable long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException {
		return claimService.getClaimsByInsuranceCompanyId(insuranceCompanyId);
	}
	
	@GetMapping("/getPlanByClaimId/{claimId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')|| hasAuthority('PATIENT')")
	public Plans getPlanByClaimId(@PathVariable long claimId) throws  NoSuchClaimFoundException {
		return claimService.getPlanByClaimid(claimId);
	}
	
	@GetMapping("/getPatientByClaimId/{claimId}")
	@PreAuthorize("hasAuthority('INSURANCE_COMPANY')|| hasAuthority('ADMIN')")
	public Patient getPatientByClaimId(@PathVariable long claimId) throws NoSuchClaimFoundException {
		return claimService.getPatientByClaimId(claimId);
	}
	
}
