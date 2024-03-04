package com.hexaware.careassist.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.careassist.entities.Admin;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 02-02-2024
Description : AdminRepository extending JpaRepository
*/

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByEmail(String email);
	
	@Query(value = "SELECT * FROM Admin WHERE admin_name LIKE %?1%", nativeQuery = true)
	List<Admin> findAdminByName(String adminName);
	
}
