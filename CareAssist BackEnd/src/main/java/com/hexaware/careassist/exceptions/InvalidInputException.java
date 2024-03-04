package com.hexaware.careassist.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
@Author :  Yash Dubey,Sheshank Sharma
Modified Date : 03-02-2024
Description : implementation of GlobalExceptionHandler
*/


public class InvalidInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public InvalidInputException(String message) {
		
		super(message);
		
		logger.error(message);
		
	}
}