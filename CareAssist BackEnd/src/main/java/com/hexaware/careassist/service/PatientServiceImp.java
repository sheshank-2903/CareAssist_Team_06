package com.hexaware.careassist.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.careassist.dto.PatientDTO;
import com.hexaware.careassist.entities.Patient;
import com.hexaware.careassist.entities.Plans;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;
import com.hexaware.careassist.exceptions.NoSuchPlanFoundException;
import com.hexaware.careassist.repository.AdminRepository;
import com.hexaware.careassist.repository.HealthCareProviderRepository;
import com.hexaware.careassist.repository.InsuranceCompanyRepository;
import com.hexaware.careassist.repository.PatientRepository;
import com.hexaware.careassist.repository.PlansRepository;

import jakarta.transaction.Transactional;

/*
@Author :  Yash Dubey
Modified Date : 02-02-2024
Description : implementation of PatientService
*/

@Service
@Transactional
public class PatientServiceImp implements IPatientService {

	Logger logger = LoggerFactory.getLogger(PatientServiceImp.class);
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	PlansRepository plansRepo;
	
	@Autowired
	InsuranceCompanyRepository insuranceCompanyRepo;
	
	@Autowired 
	AdminRepository adminRepo;
	
	@Autowired
	HealthCareProviderRepository healthCareRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	String exceptionMessage="No such patient exists in the database";
	
	@Override
	public PatientDTO getPatientById(long patientId) throws NoSuchPatientFoundException {
		
		Patient patient=patientRepo.findById(patientId)
				.orElseThrow(()->new NoSuchPatientFoundException(exceptionMessage)); 
		
		PatientDTO patientdto=new PatientDTO();
		patientdto.setPatientId(patient.getPatientId());  
		patientdto.setPatientName(patient.getPatientName());
		patientdto.setAddress(patient.getAddress());
		patientdto.setContact(patient.getContact());
		patientdto.setDob(patient.getDob());
		patientdto.setDescriptionOfTreatment(patient.getDescriptionOfTreatment());
		patientdto.setEmail(patient.getEmail());
		patientdto.setPassword(patient.getPassword());
		patientdto.setPatientGender(patient.getPatientGender());
		
		logger.info("PatientServiceImp-- Patient with id {} has been fetched successfully",patientId);
		
		return patientdto;
	}
	
	@Transactional
	@Override
	public Patient updatePatient(PatientDTO patientDto) throws NoSuchPatientFoundException, EmailAlreadyPresentException {

		Patient isPresent =patientRepo.findById(patientDto.getPatientId())
		.orElseThrow(()->new NoSuchPatientFoundException(exceptionMessage)); 

		Patient checkIfNew= patientRepo.findByEmail(patientDto.getEmail()).orElse(null);

		if( checkIfNew == null ||(isPresent.getEmail().equals(patientDto.getEmail()) )) {
			
			patientDto.setPassword(passwordEncoder.encode(patientDto.getPassword()));
			
			Patient patient=new Patient();
			patient.setPatientId(patientDto.getPatientId());
			patient.setPatientName(patientDto.getPatientName());
			patient.setAddress(patientDto.getAddress());
			patient.setContact(patientDto.getContact());
			patient.setDob(patientDto.getDob());
			patient.setDescriptionOfTreatment(patientDto.getDescriptionOfTreatment());
			patient.setEmail(patientDto.getEmail());
			patient.setPassword(patientDto.getPassword());
			patient.setPatientGender(patientDto.getPatientGender());
			
			logger.warn("PatientServiceImp-- Patient with id: {} is updated!!!!",patient.getPatientId());
			
//			return patientRepo.save(patient);
			 patientRepo.updatePatientById(patientDto.getDob(),patientDto.getContact(),patientDto.getAddress(),
					patientDto.getPatientName(),patientDto.getPatientGender(),patientDto.getDescriptionOfTreatment(),
					patientDto.getEmail(),patientDto.getPassword(),patientDto.getPatientId());
			 
			 return patientRepo.findById(patientDto.getPatientId()).orElseThrow(()->new NoSuchPatientFoundException(exceptionMessage));
			
		}else {
			
			throw new EmailAlreadyPresentException("This email is already registered in our database");
			
		}	
		
	}

	@Override
	public boolean deletePatientById(long patientId) throws NoSuchPatientFoundException {
		
		patientRepo.findById(patientId)
		.orElseThrow(()->new NoSuchPatientFoundException(exceptionMessage)); 

		patientRepo.deleteById(patientId);
		
		Patient patient=patientRepo.findById(patientId).orElse(null); 

		
		boolean bool=false;
		if(patient==null) {
			bool=true;
			logger.warn("PatientServiceImp-- Patient with id: {} has been deleted!",patientId);
		}
		
		return bool;
	
	}

	@Override
	public List<Patient> getAllPatient() {
		logger.info("PatientServiceImp-- All the Patients Data is received!!!");
		return patientRepo.findAll();
	}

	@Override
	public List<Patient> getPatientByName(String patientName) {
		
		logger.info("PatientServiceImp-- Patients with name: {}  are fetched!!!",patientName);
		
		return patientRepo.findByPatientName(patientName);
	}

	@Override
	public Patient addPatient(PatientDTO patientDto) throws EmailAlreadyPresentException {
		if(adminRepo.findByEmail(patientDto.getEmail()).orElse(null)!=null || 
				patientRepo.findByEmail(patientDto.getEmail()).orElse(null)!=null ||
				healthCareRepo.findByEmail(patientDto.getEmail()).orElse(null)!=null ||
				insuranceCompanyRepo.findByEmail(patientDto.getEmail()).orElse(null)!=null) {
			throw new EmailAlreadyPresentException("This email is already present in database");
		}
		
		patientDto.setPassword(passwordEncoder.encode(patientDto.getPassword()));
		
		Patient patient=new Patient();
		patient.setPatientId(patientDto.getPatientId());
		patient.setPatientName(patientDto.getPatientName());
		patient.setAddress(patientDto.getAddress());
		patient.setContact(patientDto.getContact());
		patient.setDob(patientDto.getDob());
		patient.setDescriptionOfTreatment(patientDto.getDescriptionOfTreatment());
		patient.setEmail(patientDto.getEmail());
		patient.setPassword(patientDto.getPassword());
		patient.setPatientGender(patientDto.getPatientGender());
		
		logger.info("PatientServiceImp-- Patient with id: {} is added successfully!!!!",patient.getPatientId());
		
		return patientRepo.save(patient);
	}

	@Override
	public String purchasePlan(long patientId, long planId) throws NoSuchPlanFoundException, NoSuchPatientFoundException {
		 	Patient patient = patientRepo.findById(patientId)
	                .orElseThrow(() -> new NoSuchPatientFoundException("Patient not found with ID: " + patientId));

	        Plans insurancePlan = plansRepo.findById(planId)
	                .orElseThrow(() -> new NoSuchPlanFoundException("Insurance plan not found with ID: " + planId));

	        int initialSize=patient.getInsurancePlans().size();
	        patient.getInsurancePlans().add(insurancePlan);
	        int finalSize=patient.getInsurancePlans().size();
	        patientRepo.save(patient);
		return finalSize>initialSize?"Purchase Successfull":"Purchase failed";
	}

	@Override
	public Set<Plans> getAllPurchasedPlans(long patientId) throws NoSuchPatientFoundException {

		Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new NoSuchPatientFoundException("Patient not found with ID: " + patientId));
		return patient.getInsurancePlans();
	}

	@Override
	public PatientDTO getPatientByEmail(String email) throws NoSuchPatientFoundException {
		
		Patient patient=patientRepo.findByEmail(email)
				.orElseThrow(()->new NoSuchPatientFoundException(exceptionMessage)); 
		
		PatientDTO patientdto=new PatientDTO();
		patientdto.setPatientId(patient.getPatientId());  
		patientdto.setPatientName(patient.getPatientName());
		patientdto.setAddress(patient.getAddress());
		patientdto.setContact(patient.getContact());
		patientdto.setDob(patient.getDob());
		patientdto.setDescriptionOfTreatment(patient.getDescriptionOfTreatment());
		patientdto.setEmail(patient.getEmail());
		patientdto.setPassword(patient.getPassword());
		patientdto.setPatientGender(patient.getPatientGender());
		
		logger.info("PatientServiceImp-- Patient  has been fetched successfully");
		
		return patientdto;
	}
	
	

}
