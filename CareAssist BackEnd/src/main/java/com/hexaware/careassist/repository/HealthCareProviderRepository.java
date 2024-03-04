package com.hexaware.careassist.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.careassist.entities.HealthCareProvider;


/*
@Author :  Sheshank Sharma
Modified Date : 02-02-2024
Description : HealthCareProviderRepository extending JpaRepository
*/

@Repository
public interface HealthCareProviderRepository extends JpaRepository<HealthCareProvider, Long>{

	Optional<HealthCareProvider> findByEmail(String email);
	
	@Query(value = "SELECT * FROM Health_care_provider WHERE health_care_provider_name LIKE %?1%", nativeQuery = true)
	List<HealthCareProvider> findHealthCareProviderByName(String healthCareProviderName);
	
}
