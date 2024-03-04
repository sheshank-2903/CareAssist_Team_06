package com.hexaware.careassist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.careassist.entities.Claims;
import com.hexaware.careassist.entities.Invoices;

/*
@Author :  Yash Dubey
Modified Date : 02-02-2024
Description : InvoicesRepository extending JpaRepository
*/

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long>{
	
	@Query("select inv from Invoices inv where inv.patient.patientId=?1")
	List<Invoices> findByByPatientId(long patientId);
	
	public List<Invoices> findByHealthCareProviderId(Long healthCareProviderId);

}
