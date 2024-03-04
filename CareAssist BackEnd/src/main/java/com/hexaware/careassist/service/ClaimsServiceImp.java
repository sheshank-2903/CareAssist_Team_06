package com.hexaware.careassist.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.careassist.dto.ClaimsDTO;
import com.hexaware.careassist.entities.Claims;
import com.hexaware.careassist.entities.Invoices;
import com.hexaware.careassist.entities.Patient;
import com.hexaware.careassist.entities.Plans;
import com.hexaware.careassist.exceptions.InvoiceNotApprovedException;
import com.hexaware.careassist.exceptions.NoSuchClaimFoundException;
import com.hexaware.careassist.exceptions.NoSuchInsuranceCompanyFoundException;
import com.hexaware.careassist.exceptions.NoSuchInvoiceFoundException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;
import com.hexaware.careassist.repository.ClaimRepository;
import com.hexaware.careassist.repository.InsuranceCompanyRepository;
import com.hexaware.careassist.repository.InvoicesRepository;
import com.hexaware.careassist.repository.PatientRepository;
import com.hexaware.careassist.repository.PlansRepository;

import jakarta.transaction.Transactional;

/*
@Author :  Sheshank Sharma
Modified Date : 02-02-2024
Description : implementation of ClaimsService
*/

@Service
public class ClaimsServiceImp implements IClaimsService {
	
	@Autowired
	ClaimRepository claimRepo;
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	PlansRepository planRepo;
	
	@Autowired
	InvoicesRepository invoiceRepo;
	
	@Autowired
	InsuranceCompanyRepository insuranceCompanyRepo;
	
	String exceptionMessage="No such claim exists in database";
	
	Logger logger =LoggerFactory.getLogger(ClaimsServiceImp.class);
	
	@Override
	public Claims addClaim(ClaimsDTO claimDto, long patientId, long planId,long invoiceId) throws NoSuchPatientFoundException, NoSuchPlanFoundException, NoSuchInvoiceFoundException, InvoiceNotApprovedException{
		
		Invoices invoice=invoiceRepo.findById(invoiceId).orElseThrow(()-> new NoSuchInvoiceFoundException("You dont have invoice to have this claim"));
		if(!"APPROVED".equals(invoice.getInvoiceStatus()))
			throw new InvoiceNotApprovedException("Your Invoice is not yet approved");
		
		Patient patient = patientRepo.findById(patientId).orElseThrow(()-> new NoSuchPatientFoundException("No such Patient exists in database"));
		Plans plans = planRepo.findById(planId).orElseThrow(()-> new NoSuchPlanFoundException("No such Plan exists in database"));
		
		if(!patient.getInsurancePlans().contains(plans)) {
			throw new NoSuchPlanFoundException("No such Plan exists in database");
		}
		
		Claims claims = claimRepo.save(new Claims(claimDto.getClaimId(),
											claimDto.getClaimAmount(),
											"PENDING",
											patient,
											plans
											));
		logger.info("ClaimsServiceImp - Claim added successfully");
		return claims;
	}

	@Transactional
	@Override
	public Claims updateClaim(String newStatus, long claimId) throws NoSuchClaimFoundException {
		Claims claim=claimRepo.findById(claimId).orElseThrow(()-> new NoSuchClaimFoundException(exceptionMessage));
		claim.setClaimStatus(newStatus);
		claim=claimRepo.save(claim);
		logger.info("ClaimsServiceImp - Claim status updated successfully");
		return claim;
	}

	@Override
	public ClaimsDTO getClaimById(long claimId)throws NoSuchClaimFoundException {
		Claims claim=claimRepo.findById(claimId).orElseThrow(()-> new NoSuchClaimFoundException(exceptionMessage));
		logger.info("ClaimsServiceImp - Claim data fetched successfully");
		return new ClaimsDTO(claim.getClaimId(),claim.getClaimAmount(),claim.getClaimStatus());
	}

	@Override
	public List<Claims> getAllClaims() {
		logger.info("ClaimsServiceImp - All Claims fetched successfully");
		return claimRepo.findAll();
	}

	@Override
	public boolean deleteClaimById(Long claimId) throws NoSuchClaimFoundException {
		claimRepo.findById(claimId).orElseThrow(()-> new NoSuchClaimFoundException(exceptionMessage));
		claimRepo.deleteById(claimId);
		Claims claim=claimRepo.findById(claimId).orElse(null);
		logger.info("ClaimsServiceImp - Claim deleted successfully");
		return claim==null;
	}

	@Override
	public List<Claims> getClaimsByPatientId(long patientId) {
		List<Claims> claim=claimRepo.findBypatientId(patientId);
		logger.info("ClaimsServiceImp - All Claim by patientId fetched successfully");
		return claim;
	}

	@Override
	public List<Claims> getClaimsByStatus(String status,Long patientId) {
		List<Claims> claim=claimRepo.getClaimByStatus(status,patientId);
		logger.info("ClaimsServiceImp - All Claim by status fetched successfully");
		return claim;
	}

	@Override
	public List<Claims> getClaimsByPlanId(long planId) {
		List<Claims> claim=claimRepo.findByplanId(planId);
		logger.info("ClaimsServiceImp - All Claim by planId fetched successfully");
		return claim;
	}

	@Override
	public List<Claims> getClaimsByInsuranceCompanyId(long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException {
		insuranceCompanyRepo.findById(insuranceCompanyId).orElseThrow(()-> new NoSuchInsuranceCompanyFoundException(exceptionMessage));
		logger.info("ClaimsServiceImp - All Claim by insuranceCompanyId fetched successfully");
		return claimRepo.findByCompanyId(insuranceCompanyId);
	}

	@Override
	public Plans getPlanByClaimid(long claimId) throws NoSuchClaimFoundException {
		claimRepo.findById(claimId).orElseThrow(()-> new NoSuchClaimFoundException(exceptionMessage));
		return claimRepo.getPlanByClaimId(claimId);
	}

	@Override
	public Patient getPatientByClaimId(long claimId) throws NoSuchClaimFoundException {
		claimRepo.findById(claimId).orElseThrow(()-> new NoSuchClaimFoundException(exceptionMessage));
		return claimRepo.getPatientByClaimId(claimId);
	}

}
