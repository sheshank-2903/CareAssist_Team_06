package com.hexaware.careassist.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.careassist.dto.AdminDTO;
import com.hexaware.careassist.entities.Admin;
import com.hexaware.careassist.entities.HealthCareProvider;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchAdminFoundException;
import com.hexaware.careassist.exceptions.NoSuchHealthCareProviderFoundException;
import com.hexaware.careassist.repository.AdminRepository;
import com.hexaware.careassist.repository.HealthCareProviderRepository;
import com.hexaware.careassist.repository.InsuranceCompanyRepository;
import com.hexaware.careassist.repository.PatientRepository;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 02-02-2024
Description : implementation of AdminService
*/

@Service
public class AdminServiceImp implements IAdminService {
	
	@Autowired 
	AdminRepository repo;
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	HealthCareProviderRepository healthCareRepo;
	
	@Autowired
	InsuranceCompanyRepository insuranceRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	Logger logger =LoggerFactory.getLogger(AdminServiceImp.class);
	
	@Override
	public AdminDTO getAdminById(long adminId) throws NoSuchAdminFoundException {
		Admin admin = repo.findById(adminId).orElseThrow(()-> new NoSuchAdminFoundException("No such admin exists in database"));
		logger.info("AdminServiceImp - Admin data fetched successfully");
		return new AdminDTO(admin.getAdminId(),admin.getAdminName(),admin.getEmail(),admin.getPassword());
	}
	

	@Override
	public Admin updateAdmin(AdminDTO adminDto) throws NoSuchAdminFoundException, EmailAlreadyPresentException {
		
		Admin isPresent=repo.findById(adminDto.getAdminId()).orElseThrow(()-> new NoSuchAdminFoundException("No such admin exists in database"));
		
		Admin checkIfNew=repo.findByEmail(adminDto.getEmail()).orElse(null);
		
		if( checkIfNew == null ||(isPresent.getEmail().equals(adminDto.getEmail()) )) {
			
			adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
			Admin admin = repo.save(new Admin(adminDto.getAdminId()
									,adminDto.getAdminName()
									,adminDto.getEmail()
									,adminDto.getPassword()));
			logger.info("AdminServiceImp - Admin has added updated successfully");
			return admin;
			}else {
				throw new EmailAlreadyPresentException("This email is already registered in our database");
			}
		
	}

	@Override
	public Admin addAdmin(AdminDTO adminDto) throws EmailAlreadyPresentException {
		if(repo.findByEmail(adminDto.getEmail()).orElse(null)!=null || 
				patientRepo.findByEmail(adminDto.getEmail()).orElse(null)!=null ||
				healthCareRepo.findByEmail(adminDto.getEmail()).orElse(null)!=null ||
				insuranceRepo.findByEmail(adminDto.getEmail()).orElse(null)!=null) {
			throw new EmailAlreadyPresentException("This email is already present in database");
		}
		adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		Admin admin = repo.save(new Admin(adminDto.getAdminId(),adminDto.getAdminName(),adminDto.getEmail(),adminDto.getPassword()));
		logger.info("AdminServiceImp - Admin has added successfull ");
		return admin;
	}


	@Override
	public AdminDTO getAdminByEmail(String email) throws NoSuchAdminFoundException {
		Admin admin=repo.findByEmail(email).orElseThrow(()-> new NoSuchAdminFoundException("No such admin exists in database"));;
		logger.info("AdminServiceImp - Admin data fetched successfully");
		return new AdminDTO(admin.getAdminId(),admin.getAdminName(),admin.getEmail(),admin.getPassword());
	}


	@Override
	public List<Admin> getAllAdmin() {
		return repo.findAll();
	}


	@Override
	public boolean deleteAdminById(long adminId) throws NoSuchAdminFoundException {
		repo.findById(adminId).orElseThrow(
				() -> new NoSuchAdminFoundException("No such admin exists in database"));
		repo.deleteById(adminId);
		Admin admin = repo.findById(adminId).orElse(null);
		logger.info("AdminServiceImp - Admin deleted successfully");
		return admin == null;
	}


	@Override
	public List<Admin> getAdminByName(String adminName) {
		return repo.findAdminByName(adminName);
	}


}
