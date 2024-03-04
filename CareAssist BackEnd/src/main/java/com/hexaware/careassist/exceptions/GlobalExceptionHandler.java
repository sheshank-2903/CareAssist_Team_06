package com.hexaware.careassist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 03-02-2024
Description : implementation of GlobalExceptionHandler
*/

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchPatientFoundException.class)

	public ResponseEntity<String> handleNoSuchPatientFoundException(NoSuchPatientFoundException ex) {

		return new ResponseEntity<String>("No such Patient exists in database", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoSuchAdminFoundException.class)

	public ResponseEntity<String> handleNoSuchAdminFoundException(NoSuchAdminFoundException ex) {

		return new ResponseEntity<String>("No such admin exists in database", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoSuchClaimFoundException.class)
	public ResponseEntity<String> handleNoSuchClaimFoundException(NoSuchClaimFoundException ex) {

		return new ResponseEntity<String>("No such claim exists in database", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoSuchHealthCareProviderFoundException.class)
	public ResponseEntity<String> handleNoSuchHealthCareProviderFoundException(NoSuchHealthCareProviderFoundException ex) {

		return new ResponseEntity<String>("No such health care provider exists in database", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoSuchInvoiceFoundException.class)

	public ResponseEntity<String> handleNoSuchInvoiceFoundException(NoSuchInvoiceFoundException ex) {

		return new ResponseEntity<String>("No such invoice exists in database", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoSuchPlanFoundException.class)

	public ResponseEntity<String> handleNoSuchPlanFoundException(NoSuchPlanFoundException ex) {

		return new ResponseEntity<String>("No such plan exists in database", HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(EmailAlreadyPresentException.class)

	public ResponseEntity<String> handleEmailAlreadyPresentException(EmailAlreadyPresentException ex) {

		return new ResponseEntity<String>("This email is already registered in our database", HttpStatus.FOUND);

	}
	
	@ExceptionHandler(InvalidDueDateException.class)

	public ResponseEntity<String> handleInvalidDueDateException(InvalidDueDateException ex) {

		return new ResponseEntity<String>("Due Date cannot be less then Invoice Date of issue", HttpStatus.EXPECTATION_FAILED);

	}
	
	@ExceptionHandler(InvoiceNotApprovedException.class)
	public ResponseEntity<String> handleInvoiceNotApprovedException(InvoiceNotApprovedException ex) {

		return new ResponseEntity<String>("Your Invoice is not yet approved", HttpStatus.FOUND);

	}

	
	@ExceptionHandler(InvalidInputException.class)

	public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {

		return new ResponseEntity<String>("Invalid Input", HttpStatus.EXPECTATION_FAILED);

	}
}
