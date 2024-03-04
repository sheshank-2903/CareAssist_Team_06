package com.hexaware.careassist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.careassist.entities.Plans;


/*
@Author :  Yash Dubey
Modified Date : 02-02-2024
Description : PlansRepository extending JpaRepository
*/

@Repository
public interface PlansRepository extends JpaRepository<Plans, Long>{

	@Query(value = "SELECT * FROM Plans WHERE plan_name LIKE %?1%", nativeQuery = true)
	List<Plans> findByPlanName(String planName);
	
	@Query(value = "SELECT * FROM Plans WHERE plan_name LIKE %?1% && insurance_company_id=?2", nativeQuery = true)
	List<Plans> findByPlanNameAndCompanyId(String planName,long insuranceCompanyId);
	
	@Query("select plans from Plans  plans where plans.insuranceCompany.companyName=?1")
	List<Plans> findByCompanyName(String companyName);
	
	@Query("select plans from Plans  plans where plans.insuranceCompany.insuranceCompanyId=?1")
	List<Plans> findByCompanyId(long insuranceCompanyId);
	
	List<Plans> findByCoverageAmountLessThan(double coverageAmount);
	
	@Query(value="select insurance_plan_id from patient_insurance_plan where patient_id=?1",nativeQuery=true)
	List<Long> findByPatientId(long patientId);


}
