package com.hexaware.careassist.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careassist.dto.ClaimsDTO;
import com.hexaware.careassist.entities.Claims;
import com.hexaware.careassist.exceptions.InvoiceNotApprovedException;
import com.hexaware.careassist.exceptions.NoSuchClaimFoundException;
import com.hexaware.careassist.exceptions.NoSuchInvoiceFoundException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;

/*
@Author :  Sheshank Sharma
Modified Date : 04-02-2024
Description : Testing of ClaimsService
*/

@SpringBootTest
class ClaimsServiceImpTest {
	
	@Autowired
	IClaimsService service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Disabled
	void testAddClaim() throws NoSuchPatientFoundException, NoSuchPlanFoundException, NoSuchInvoiceFoundException, InvoiceNotApprovedException {
		Claims claims = service.addClaim(new ClaimsDTO(2,1000.00,"askjb"), 1, 1,1);
		assertTrue(claims.getClaimStatus()=="pending" && claims.getClaimAmount()==1000.00);
	}

	@Test
	@Disabled
	void testUpdateclaim() throws NoSuchClaimFoundException {
		Claims claims = service.updateClaim("pending",1);
		assertEquals("pending",claims.getClaimStatus());
	}

	@Test
	@Disabled
	void testGetClaimById() throws NoSuchClaimFoundException {
		ClaimsDTO claim=service.getClaimById(1);
		assertEquals("pending",claim.getClaimStatus());
	}

	@Test
	@Disabled
	void testGetAllClaims() {
		List<Claims> response = service.getAllClaims();
		assertEquals(2,response.size());
	}

	@Test
	@Disabled
	void testDeleteClaimById() throws NoSuchClaimFoundException {
		boolean response = service.deleteClaimById((long) 1);
		assertTrue(response);
	}

	@Test
	@Disabled
	void testGetClaimsByPatientId() {
		List<Claims> response = service.getClaimsByPatientId(1);
		assertEquals(2,response.size());
	}

	@Test
	@Disabled
	void testGetClaimsByStatus() {
		List<Claims> response = service.getClaimsByStatus("REJECTED",1L);
		assertEquals(0,response.size());
	}

	@Test
	@Disabled
	void testGetClaimsByPlanId() {
		List<Claims> response = service.getClaimsByPlanId(1);
		assertEquals(2,response.size());
	}

}
