package com.hexaware.careassist.service;

import java.util.List;
import java.util.Set;

import com.hexaware.careassist.dto.PatientDTO;
import com.hexaware.careassist.entities.Patient;
import com.hexaware.careassist.entities.Plans;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;

/*
@Author :  Yash Dubey
Modified Date : 02-02-2024
Description : creation of PatientService Interface
*/

public interface IPatientService {
	public Patient addPatient(PatientDTO patientDto) throws EmailAlreadyPresentException;
	public PatientDTO getPatientById(long patientId) throws NoSuchPatientFoundException;
	public Patient updatePatient(PatientDTO patientDto) throws NoSuchPatientFoundException, EmailAlreadyPresentException;
	public boolean deletePatientById(long patientId) throws NoSuchPatientFoundException;
	public List<Patient> getAllPatient();
	public List<Patient> getPatientByName(String patientName);
	public String purchasePlan(long patientId,long planId) throws NoSuchPlanFoundException, NoSuchPatientFoundException;
	public Set<Plans> getAllPurchasedPlans(long patientId) throws NoSuchPatientFoundException;
	public PatientDTO getPatientByEmail(String email) throws NoSuchPatientFoundException;
}
