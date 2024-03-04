package com.hexaware.careassist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careassist.dto.PlansDTO;
import com.hexaware.careassist.entities.Plans;
import com.hexaware.careassist.exceptions.InvalidInputException;
import com.hexaware.careassist.exceptions.NoSuchInsuranceCompanyFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;

/*
@Author :  Yash Dubey
Modified Date : 04-02-2024
Description : Testing of PlansService
*/

@SpringBootTest
class PlansServiceImpTest {

	@Autowired
	IPlansService service;
	
	@Test
	@Disabled
	void testAddPlan() throws NoSuchInsuranceCompanyFoundException {
		PlansDTO plansDto=new PlansDTO(2,"aaaa","aaaaa",LocalDate.now(),60000);
		Plans plan=service.addPlan(plansDto, 2);
		assertEquals(2,plan.getPlanId());	
	}

	@Test
	@Disabled
	void testUpdatePlan() throws NoSuchPlanFoundException {
		//PlansDTO plansDto=new PlansDTO(1,"bbbb","aaaaa",LocalDate.now(),65000);
		Plans plan=service.updatePlan("tut","djsdjsdjs",60000,3);
		assertEquals(60000.0,plan.getCoverageAmount());
	}

	@Test
	@Disabled
	void testDeletePlanById() throws NoSuchPlanFoundException {
		boolean bool=service.deletePlanById(2);
		assertTrue(bool);
	}

	@Test
	@Disabled
	void testGetPlanById() throws NoSuchPlanFoundException {
		PlansDTO plan=service.getPlanById(2);
		assertEquals(2,plan.getPlanId());
	}

	@Test
	@Disabled
	void testGetAllPlans() {
		List<Plans> list=service.getAllPlans();
		assertEquals(2,list.size());
	}

	@Test
	@Disabled
	void testGetPlanByName() {
		List<Plans> list=service.getPlanByName("aaaa");  
		assertEquals(1,list.size());
	}

	@Test
	@Disabled
	void testGetPlanByInsuranceCompanyName() throws NoSuchInsuranceCompanyFoundException {
		List<Plans> list=service.getPlanByInsuranceCompanyName("abas");
		assertEquals(1,list.size());
	}
	
	@Test
	@Disabled
	void testGetPlanByCoverageAmountLessthan() throws InvalidInputException {
		List<Plans> list=service.getPlanyByAmountLessThan(25000);
		assertEquals(1,list.size());
	}

}
