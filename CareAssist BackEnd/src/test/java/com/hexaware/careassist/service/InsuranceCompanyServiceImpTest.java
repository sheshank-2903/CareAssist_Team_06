package com.hexaware.careassist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careassist.dto.InsuranceCompanyDTO;
import com.hexaware.careassist.entities.InsuranceCompany;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchInsuranceCompanyFoundException;

/*
@Author :  Sheshank Sharma
Modified Date : 04-02-2024
Description : Testing of InsuranceCompanyService
*/

@SpringBootTest
class InsuranceCompanyServiceImpTest {
	
	@Autowired
	IInsuranceCompanyService service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Disabled
	void testGetInsuranceCompanyById() throws NoSuchInsuranceCompanyFoundException {
		InsuranceCompanyDTO response=service.getInsuranceCompanyById(1);
		assertEquals(1,response.getInsuranceCompanyId());
	}

	@Test
	@Disabled
	void testUpdateInsuranceCompany() throws NoSuchInsuranceCompanyFoundException, EmailAlreadyPresentException {
		InsuranceCompany response= service.updateInsuranceCompany(new InsuranceCompanyDTO((long)1,"askdjf  ","abas","6367016451","abc@gmail.com","Yash@123"));
		assertSame("abas",response.getCompanyName());
	}

	@Test
	@Disabled
	void testDeleteInsuranceCompanyById() throws NoSuchInsuranceCompanyFoundException {
		boolean response=service.deleteInsuranceCompanyById(1);
		assertTrue(response);
	}

	@Test
	@Disabled
	void testAddInsuranceCompany() throws EmailAlreadyPresentException {
		InsuranceCompany response=service.addInsuranceCompany(new InsuranceCompanyDTO((long)5,"description","name","6367016451","abc@gmail.com","Yash@123"));
		assertTrue(response.getCompanyName()=="name" && response.getInsuranceCompanyDescription()=="description");
	}

	@Test
	@Disabled
	void testGetInsuranceCompanyByName() throws NoSuchInsuranceCompanyFoundException {
		List<InsuranceCompany> response=service.getInsuranceCompanyByName("name2");
		assertTrue(response.size()>0);
		
	}
	
	@Test
	@Disabled
	void testGetAllInsuranceCompany() {
		List<InsuranceCompany> response=service.getAllInsuranceCompany();
		assertTrue(response.size()>0);
		
	}

}
