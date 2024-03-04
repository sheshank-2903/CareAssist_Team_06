package com.hexaware.careassist.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
@Author :  Sheshank Sharma
Modified Date : 03-02-2024
Description : Creation of EmailAlreadyPresentException
*/


public class EmailAlreadyPresentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public EmailAlreadyPresentException(String message) {
		
		super(message);
		
		logger.error(message);
		
	}
}