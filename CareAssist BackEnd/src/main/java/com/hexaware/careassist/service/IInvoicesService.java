package com.hexaware.careassist.service;

import java.util.List;

import com.hexaware.careassist.dto.InvoicesDTO;
import com.hexaware.careassist.entities.Invoices;
import com.hexaware.careassist.exceptions.InvalidDueDateException;
import com.hexaware.careassist.exceptions.NoSuchHealthCareProviderFoundException;
import com.hexaware.careassist.exceptions.NoSuchInvoiceFoundException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;

/*
@Author :  Sheshank Sharma
Modified Date : 02-02-2024
Description : creation of InvoiceService Interface
*/

public interface IInvoicesService {
	public Invoices addInvoice(InvoicesDTO invoiceDto,long patientId) throws NoSuchPatientFoundException, InvalidDueDateException, NoSuchHealthCareProviderFoundException;
	public List<Invoices> getAllInvoices();
	public Invoices getInvoiceById(long invoiceId) throws NoSuchInvoiceFoundException;
	public List<Invoices> getInvoicesByPatientId(long patientId) throws NoSuchPatientFoundException;
	public boolean deleteInvoiceById(long invoiceId) throws NoSuchInvoiceFoundException;
	public Invoices updateInvoiceStatusById(long invoiceId,String invoiceStatus) throws NoSuchInvoiceFoundException;
	public List<Invoices> getInvoicesByHealthCareProviderId(long healthCareProviderId) throws NoSuchHealthCareProviderFoundException;
	
}
