package com.hexaware.careassist.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoiceNotApprovedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public InvoiceNotApprovedException(String message) {
		
		super(message);
		
		logger.error(message);
		
	}
}