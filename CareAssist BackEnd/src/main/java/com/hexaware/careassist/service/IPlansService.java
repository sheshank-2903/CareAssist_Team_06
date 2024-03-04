package com.hexaware.careassist.service;

import java.util.List;

import com.hexaware.careassist.dto.PlansDTO;
import com.hexaware.careassist.entities.Plans;
import com.hexaware.careassist.exceptions.InvalidInputException;
import com.hexaware.careassist.exceptions.NoSuchInsuranceCompanyFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;

/*
@Author :  Yash Dubey
Modified Date : 02-02-2024
Description : creation of PlansService Interface
*/

public interface IPlansService {
	public Plans addPlan(PlansDTO plansDto,long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException;
	public Plans updatePlan(String planName,String description,double coverageAmount,long planId) throws NoSuchPlanFoundException;
	public boolean deletePlanById(long planId) throws NoSuchPlanFoundException;
	public PlansDTO getPlanById(long planId) throws NoSuchPlanFoundException;
	public List<Plans> getAllPlans();
	public List<Plans> getPlanByName(String planName);
	public List<Plans> getPlanByNameAndCompanyId(String planName,long insuranceCompanyId);
	public List<Plans> getPlanByInsuranceCompanyName(String companyName) throws NoSuchInsuranceCompanyFoundException;
	public List<Plans> getPlanByInsuranceCompanyId(long insuranceCompanyId) throws NoSuchInsuranceCompanyFoundException;
	public List<Plans> getPlanyByAmountLessThan(double coverageAmount) throws InvalidInputException;
	public List<Plans> getPlanyByPatientId(long patientId);
	
}
