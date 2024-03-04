package com.hexaware.careassist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.careassist.entities.Claims;
import com.hexaware.careassist.entities.Patient;
import com.hexaware.careassist.entities.Plans;

/*
@Author :  Sheshank Sharma
Modified Date : 02-02-2024
Description : ClaimRepository extending JpaRepository
*/

@Repository
public interface ClaimRepository extends JpaRepository<Claims, Long> {

		@Query("select c from Claims c where c.patient.patientId= :patientId")
		public List<Claims> findBypatientId(@Param("patientId") long patientId);
		
		
		@Query(value = "SELECT * FROM Claims c WHERE c.claim_status = :claimStatus AND c.patient_id = :patientId", nativeQuery = true)
		public List<Claims> getClaimByStatus(@Param("claimStatus") String claimStatus, @Param("patientId") Long patientId);

		@Query("select c from Claims c where c.plans.planId= :planId")
		public List<Claims> findByplanId(@Param("planId") long planId);
		
		@Query("select c from Claims c where c.plans.insuranceCompany.insuranceCompanyId= :insuranceComapnyId")
		public List<Claims> findByCompanyId(@Param("insuranceComapnyId") long insuranceComapnyId);
		
		@Query("select c.plans from Claims c where c.claimId=?1")
		public Plans getPlanByClaimId(long claimId);
		
		@Query("select c.patient from Claims c where c.claimId=?1")
		public Patient getPatientByClaimId(long claimId);

}
