package com.hexaware.careassist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.careassist.entities.InsuranceCompany;

/*
@Author :  Sheshank Sharma
Modified Date : 02-02-2024
Description : InsuranceCompanyRepository extending JpaRepository
*/

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long>{
	
	@Query(value = "SELECT * FROM Insurance_company WHERE company_name LIKE %?1%", nativeQuery = true)
	public List<InsuranceCompany> findByCompanyName(String companyName);

	public Optional<InsuranceCompany> findByEmail(String email);
}
