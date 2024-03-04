package com.hexaware.careassist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.careassist.dto.InvoicesDTO;
import com.hexaware.careassist.entities.Invoices;
import com.hexaware.careassist.exceptions.InvalidDueDateException;
import com.hexaware.careassist.exceptions.NoSuchHealthCareProviderFoundException;
import com.hexaware.careassist.exceptions.NoSuchInvoiceFoundException;
import com.hexaware.careassist.exceptions.NoSuchPatientFoundException;

/*
@Author :  Yash Dubey
Modified Date : 04-02-2024
Description : Testing of InvoicesService
*/

@SpringBootTest
class InvoicesServiceImpTest {

	@Autowired
	IInvoicesService service;

	@Test
	@Disabled
	void testAddInvoice() throws NoSuchPatientFoundException, InvalidDueDateException, NoSuchHealthCareProviderFoundException {
		//InvoicesDTO invoice=new InvoicesDTO(2,LocalDate.now(),LocalDate.now(),"Yash","abc abc", 0, 0, 0, 0, 0);
		InvoicesDTO invoice=new InvoicesDTO(2,LocalDate.now(),LocalDate.now(),0, 0, 0, 0,1);
		Invoices inv=service.addInvoice(invoice, 1);
		assertEquals(2,inv.getInvoiceId());
	}

	@Test
	@Disabled
	void testGetAllInvoices() {
		List<Invoices> list=service.getAllInvoices();
		assertEquals(2,list.size());
	}

	@Test
	@Disabled
	void testGetInvoiceById() throws NoSuchInvoiceFoundException {
		Invoices inv=service.getInvoiceById(1);
		assertEquals(1,inv.getInvoiceId());
	}

	@Test
	@Disabled
	void testGetInvoicesByPatientId() throws NoSuchPatientFoundException {
		List<Invoices> list=service.getInvoicesByPatientId(1);
		assertEquals(2,list.size());
	}

}
