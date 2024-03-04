package com.hexaware.careassist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.careassist.dto.InvoicesDTO;
import com.hexaware.careassist.entities.Invoices;
import com.hexaware.careassist.exceptions.InvalidDueDateException;
import com.hexaware.careassist.exceptions.NoSuchHealthCareProviderFoundException;
import com.hexaware.careassist.exceptions.NoSuchInvoiceFoundException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;
import com.hexaware.careassist.service.IInvoicesService;

/*
@Author :  Yash Dubey
Modified Date : 05-02-2024
Description : Creation of InvoicesRestController
*/

@RestController
@RequestMapping("/api/v1/invoice")
@CrossOrigin(origins="http://localhost:4200")
public class InvoicesRestController {

	@Autowired
	IInvoicesService service;
	
	@PostMapping("/add/{patientId}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public Invoices addInvoice(@RequestBody InvoicesDTO invoiceDto,@PathVariable long patientId) throws NoSuchPatientFoundException, InvalidDueDateException, NoSuchHealthCareProviderFoundException {
	
		return service.addInvoice(invoiceDto, patientId);
	}

	
	@GetMapping("/getAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Invoices> getAllInvoices() {
		return service.getAllInvoices();
	}

	
	@GetMapping("/get/{invoiceId}")
	@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HEALTH_CARE_PROVIDER') || hasAuthority('PATIENT')")
	public Invoices getInvoiceById(@PathVariable long invoiceId) throws NoSuchInvoiceFoundException {
		return service.getInvoiceById(invoiceId);
	}

	
	@GetMapping("/getByPatientId/{patientId}")
	@PreAuthorize("hasAuthority('PATIENT')")
	public List<Invoices> getInvoicesByPatientId(@PathVariable long patientId) throws NoSuchPatientFoundException {
		return service.getInvoicesByPatientId(patientId);
	}
	
	@GetMapping("/getByHealthCareProviderId/{healthCareProviderId}")
	@PreAuthorize("hasAuthority('HEALTH_CARE_PROVIDER')")
	public List<Invoices> getInvoicesByHealthCareProviderId(@PathVariable long healthCareProviderId) throws NoSuchHealthCareProviderFoundException {
		return service.getInvoicesByHealthCareProviderId(healthCareProviderId);
	}
	
	@PutMapping("/updateInvoiceStatus/{invoiceId}/{invoiceStatus}")
	@PreAuthorize("hasAuthority('HEALTH_CARE_PROVIDER')")
	public Invoices updateInvoiceStatus(@PathVariable long invoiceId,@PathVariable String invoiceStatus) throws NoSuchInvoiceFoundException {
		return service.updateInvoiceStatusById(invoiceId, invoiceStatus);
	}
	
	@DeleteMapping("/delete/{invoiceId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public boolean deleteInvoiceById(@PathVariable long invoiceId) throws NoSuchInvoiceFoundException {
		return service.deleteInvoiceById(invoiceId);
	}
	
	
	

}
