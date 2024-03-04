package com.hexaware.careassist.service;

import java.util.List;

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

/*
@Author : Sheshank Sharma
Modified Date : 02-02-2024
Description : Creation of ClaimsService Interface
*/

public interface IClaimsService {
	public Claims addClaim(ClaimsDTO claimDto, long patientId, long planId,long invoiceId) throws NoSuchPatientFoundException, NoSuchPlanFoundException, NoSuchInvoiceFoundException, InvoiceNotApprovedException;
	public Claims updateClaim(String newStatus, long claimId) throws NoSuchClaimFoundException;
	public ClaimsDTO getClaimById(long claimId) throws NoSuchClaimFoundException;
	public List<Claims> getAllClaims();
	public boolean deleteClaimById(Long claimId) throws NoSuchClaimFoundException;
	public List<Claims> getClaimsByPatientId(long patientId);
	public List<Claims> getClaimsByStatus(String status,Long patientId);
	public List<Claims> getClaimsByPlanId(long planId);
	public List<Claims> getClaimsByInsuranceCompanyId(long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException;
	public Plans getPlanByClaimid(long claimId) throws NoSuchClaimFoundException;
	public Patient getPatientByClaimId(long claimId) throws NoSuchClaimFoundException;
}
