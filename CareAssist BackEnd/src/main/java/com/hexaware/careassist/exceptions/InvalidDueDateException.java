package com.hexaware.careassist.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
@Author :  Yash Dubey
Modified Date : 03-02-2024
Description : Creation of InvalidDueDateException
*/


public class InvalidDueDateException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public InvalidDueDateException(String message) {
		
		super(message);
		
		logger.error(message);
		
	}
}