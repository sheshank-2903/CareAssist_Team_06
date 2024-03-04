package com.hexaware.careassist.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.careassist.entities.Patient;


/*
@Author :  Yash Dubey
Modified Date : 02-02-2024
Description : PatientRepository extending JpaRepository
*/

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	@Query(value = "SELECT * FROM Patient WHERE patient_Name LIKE %?1%", nativeQuery = true)
	public List<Patient> findByPatientName(String patientName);

	public Optional<Patient> findByEmail(String email);
	
	@Modifying
	@Query("update Patient p set p.dob=?1,p.contact=?2,p.address=?3,p.patientName=?4,p.patientGender=?5,p.descriptionOfTreatment=?6,p.email=?7,p.password=?8 where p.patientId=?9")
	public void updatePatientById(LocalDate dob,String contact,String address,String patientName,String patientGender,String descriptionOfTreatment,String email,String password,long patientId);
	
}
