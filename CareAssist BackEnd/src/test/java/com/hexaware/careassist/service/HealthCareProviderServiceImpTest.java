package com.hexaware.careassist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careassist.dto.HealthCareProviderDTO;
import com.hexaware.careassist.entities.HealthCareProvider;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchHealthCareProviderFoundException;

/*
@Author :  Sheshank Sharma
Modified Date : 04-02-2024
Description : Testing of HealthCareProviderService
*/

@SpringBootTest
class HealthCareProviderServiceImpTest {

	@Autowired
	IHealthCareProviderService service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Disabled
	void testAddHealthCareProvider() throws EmailAlreadyPresentException {
		HealthCareProvider response=service.addHealthCareProvider(new HealthCareProviderDTO(2,"sheshank","male","absc 123 sadbas","abc@gmail.com","asdkjf"));
		assertTrue(response.getHealthCareProviderName()=="sheshank" && response.getEmail()=="abc@gmail.com");
	}

	@Test
	@Disabled
	void testGetHealthCareProviderById() throws NoSuchHealthCareProviderFoundException {
		HealthCareProviderDTO response=service.getHealthCareProviderById(1);
		assertEquals(1,response.getHealthCareProviderId());
	}

	@Test
	@Disabled
	void testUpdateHealthCareProviderInfo() throws NoSuchHealthCareProviderFoundException, EmailAlreadyPresentException {
		HealthCareProvider response= service.updateHealthCareProvider(new HealthCareProviderDTO(
				1, "king",
				"male", "ashdhfiasd faisdhfisad fsaidufh",
				"bht@gmail.com", "asdkjksdfj"));
		assertEquals("king",response.getHealthCareProviderName());
	}

	@Test
	@Disabled
	void testDeleteHealthCareProviderInfo() throws NoSuchHealthCareProviderFoundException {
		boolean response=service.deleteHealthCareProvider(2);
		assertTrue(response);
	}

	@Test
	@Disabled
	void testGetAllHealthCareProvider() {
		List<HealthCareProvider> response=service.getAllHealthCareProvider();
		assertEquals(2,response.size());
	}

}
