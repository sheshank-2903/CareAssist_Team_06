package com.hexaware.careassist.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.careassist.dto.InvoicesDTO;
import com.hexaware.careassist.entities.Invoices;
import com.hexaware.careassist.entities.Patient;
import com.hexaware.careassist.exceptions.InvalidDueDateException;
import com.hexaware.careassist.exceptions.NoSuchHealthCareProviderFoundException;
import com.hexaware.careassist.exceptions.NoSuchInvoiceFoundException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;
import com.hexaware.careassist.repository.HealthCareProviderRepository;
import com.hexaware.careassist.repository.InvoicesRepository;
import com.hexaware.careassist.repository.PatientRepository;

import jakarta.transaction.Transactional;

/*
@Author :  Yash Dubey
Modified Date : 02-02-2024
Description : implementation of InvoicesService
*/

@Service
@Transactional
public class InvoicesServiceImp implements IInvoicesService {

	Logger logger = LoggerFactory.getLogger(InvoicesServiceImp.class);
	
	@Autowired
	InvoicesRepository invoiceRepo;
	
	@Autowired
	HealthCareProviderRepository healthCareProviderRepo;
	
	@Autowired
	PatientRepository patientRepo;
	
	@Override
	public Invoices addInvoice(InvoicesDTO invoiceDto,long patientId) throws NoSuchPatientFoundException, InvalidDueDateException, NoSuchHealthCareProviderFoundException {
		
		Patient patient=patientRepo.findById(patientId)
				.orElseThrow(()->new NoSuchPatientFoundException("No such patient exists in the database")); 
		
		healthCareProviderRepo.findById(invoiceDto.getHealthCareProviderId())
		.orElseThrow(()->new NoSuchHealthCareProviderFoundException("No such HealthCareProvider Found in the database")); 
		
		if (invoiceDto.getInvoiceDueDate().isBefore(invoiceDto.getInvoiceDate())) {
		    throw new InvalidDueDateException("Due Date must be on or after the Invoice Date of issue");
		}
		
		
	
		Invoices invoice=new Invoices();
		invoice.setInvoiceId(invoiceDto.getInvoiceId());
		invoice.setInvoiceDate(invoiceDto.getInvoiceDate());
		invoice.setInvoiceDueDate(invoiceDto.getInvoiceDueDate());
		invoice.setPatientName(patient.getPatientName());
		invoice.setPatientAddress(patient.getAddress());
		invoice.setInvoiceTax(invoiceDto.getInvoiceTax());
		invoice.setConsultingFees(invoiceDto.getConsultingFees());
		invoice.setDiagnosticTestFees(invoiceDto.getDiagnosticTestFees());
		invoice.setDiagnosticScanFees(invoiceDto.getDiagnosticScanFees());
		invoice.setCalculatedAmount(invoiceDto.getCalculatedAmount());
		invoice.setPatient(patient);
		invoice.setHealthCareProviderId(invoiceDto.getHealthCareProviderId());
		invoice.setInvoiceStatus("PENDING");
		
		logger.info("InvoicesServiceImp-- Invoice with invoiceId: {} generated successfully",invoice.getInvoiceId());
		
		patient.getInvoiceSet().add(invoice);
		patientRepo.save(patient);
		
		return invoiceRepo.save(invoice);
		
	}

	

	@Override
	public List<Invoices> getAllInvoices() {
		logger.info("InvoicesServiceImp-- All invoice details fetched successfully");
		return invoiceRepo.findAll();
	}

	@Override
	public Invoices getInvoiceById(long invoiceId) throws NoSuchInvoiceFoundException {

		logger.info("InvoicesServiceImp-- Invoice with invoiceId: {} fetched successfully",invoiceId);
		return invoiceRepo.findById(invoiceId).orElseThrow(()->new NoSuchInvoiceFoundException("No such Invoice exists in the database")); 
		
	}
	

	@Override
	public List<Invoices> getInvoicesByPatientId(long patientId) throws NoSuchPatientFoundException {
		patientRepo.findById(patientId)
		.orElseThrow(()->new NoSuchPatientFoundException("No such patient exists in the database")); 

		logger.info("InvoicesServiceImp-- Invoice with PatientId: {} fetched successfully",patientId);
		return invoiceRepo.findByByPatientId(patientId);
	}



	@Override
	public boolean deleteInvoiceById(long invoiceId) throws NoSuchInvoiceFoundException {
		invoiceRepo.findById(invoiceId)
		.orElseThrow(()->new NoSuchInvoiceFoundException("No such Invoice exists in the database")); 
		
		invoiceRepo.deleteById(invoiceId);
		logger.info("InvoicesServiceImp -- invoice deleted successfully");
		return invoiceRepo.findById(invoiceId).orElse(null)==null;
	}



	@Override
	public Invoices updateInvoiceStatusById(long invoiceId,String invoiceStatus) throws NoSuchInvoiceFoundException {
		Invoices invoice=invoiceRepo.findById(invoiceId).orElseThrow(()-> new NoSuchInvoiceFoundException("No such Invoice exists in the database")); 
		invoice.setInvoiceStatus(invoiceStatus);
		invoiceRepo.save(invoice);
		logger.info("InvoicesServiceImp-- Invoice updated successfully");
		return invoice;
	}



	@Override
	public List<Invoices> getInvoicesByHealthCareProviderId(long healthCareProviderId) throws NoSuchHealthCareProviderFoundException {
		healthCareProviderRepo.findById(healthCareProviderId)
		.orElseThrow(()->new NoSuchHealthCareProviderFoundException("No such HealthCareProvider Found in the database")); 
		
		return invoiceRepo.findByHealthCareProviderId(healthCareProviderId);
	}

	

}
