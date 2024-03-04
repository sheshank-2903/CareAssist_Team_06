package com.hexaware.careassist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careassist.dto.AdminDTO;
import com.hexaware.careassist.entities.Admin;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchAdminFoundException;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 04-02-2024
Description : Testing of AdminService
*/

@SpringBootTest
class AdminServiceImpTest {

	@Autowired
	IAdminService  service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Disabled
	void testGetAdminInfo() throws NoSuchAdminFoundException {
		AdminDTO response=service.getAdminById(1);
		assertEquals(1l,response.getAdminId());
	}

	@Test
	@Disabled
	void testUpdateAdminInfo() throws NoSuchAdminFoundException, EmailAlreadyPresentException {
		Admin response=service.updateAdmin(new AdminDTO(1,"name","admin@gmail.com","password"));
		assertTrue(response.getAdminName()=="name" && response.getEmail()=="admin@gmail.com");
	}

	@Test
	@Disabled
	void testAddAdmin() throws EmailAlreadyPresentException {
		Admin response=service.addAdmin(new AdminDTO(2,"king","email@gmail.com","password"));
		assertTrue(response.getAdminName()=="king" && response.getEmail()=="email@gmail.com");
	}

}
