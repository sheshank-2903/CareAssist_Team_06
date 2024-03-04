package com.hexaware.careassist.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hexaware.careassist.entities.Admin;
import com.hexaware.careassist.entities.HealthCareProvider;
import com.hexaware.careassist.entities.InsuranceCompany;
import com.hexaware.careassist.entities.Patient;
import com.hexaware.careassist.repository.AdminRepository;
import com.hexaware.careassist.repository.HealthCareProviderRepository;
import com.hexaware.careassist.repository.InsuranceCompanyRepository;
import com.hexaware.careassist.repository.PatientRepository;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 09-02-2024
Description : Creation of UserInfoDetailsService
*/

@Component
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	AdminRepository adminRepo;

	@Autowired
	PatientRepository patientRepo;

	@Autowired
	InsuranceCompanyRepository insuranceCompanyRepo;

	@Autowired
	HealthCareProviderRepository healthCareProviderRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Admin> adminInfo = adminRepo.findByEmail(email);

		Optional<Patient> patientInfo = patientRepo.findByEmail(email);

		Optional<InsuranceCompany> insuranceCompanyInfo = insuranceCompanyRepo.findByEmail(email);

		Optional<HealthCareProvider> healthCareProviderInfo = healthCareProviderRepo.findByEmail(email);

		if (adminInfo.isPresent()) {
			return adminInfo.map(AdminInfoAdminDetails::new)
					.orElseThrow(() -> new UsernameNotFoundException("No Admin with this email"));

		} else if (patientInfo.isPresent()) {
			
			return patientInfo.map(PatientInfoPatientDetails::new)
					.orElseThrow(() -> new UsernameNotFoundException("No Patient with this email"));

		} else if (insuranceCompanyInfo.isPresent()) {
			
			return insuranceCompanyInfo.map(CompanyInfoCompanyDetails::new)
					.orElseThrow(() ->new  UsernameNotFoundException("No Insurance company with this email"));


		}
		else if (healthCareProviderInfo.isPresent()) {
			
			return healthCareProviderInfo.map(HealthCareInfoHealthCareDetails::new)
					.orElseThrow(() ->new  UsernameNotFoundException("No Health Care Provider with this email"));
		}
		
		throw new UsernameNotFoundException("No such email exists in Database");
		

	}

}
