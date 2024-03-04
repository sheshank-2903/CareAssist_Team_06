package com.hexaware.careassist.service;
import java.util.List;

import com.hexaware.careassist.dto.HealthCareProviderDTO;
import com.hexaware.careassist.entities.HealthCareProvider;
import com.hexaware.careassist.exceptions.EmailAlreadyPresentException;
import com.hexaware.careassist.exceptions.NoSuchHealthCareProviderFoundException;

/*
@Author :  Sheshank Sharma
Modified Date : 02-02-2024
Description : creation of HealthCareProvider Interface
*/

public interface IHealthCareProviderService {
	public HealthCareProvider addHealthCareProvider(HealthCareProviderDTO healthCareProviderDto) throws EmailAlreadyPresentException;
	public HealthCareProviderDTO getHealthCareProviderById(long healthCareProviderId) throws NoSuchHealthCareProviderFoundException;
	public HealthCareProvider updateHealthCareProvider(HealthCareProviderDTO healthCareProviderDto) throws NoSuchHealthCareProviderFoundException, EmailAlreadyPresentException;
	public boolean deleteHealthCareProvider(long healthCareProviderId) throws NoSuchHealthCareProviderFoundException;
	public List<HealthCareProvider> getAllHealthCareProvider();
	public HealthCareProviderDTO getHealthCareProviderByEmail(String email) throws NoSuchHealthCareProviderFoundException;
	public List<HealthCareProvider> getHealthCareProviderByName(String healthCareProviderName);
}
