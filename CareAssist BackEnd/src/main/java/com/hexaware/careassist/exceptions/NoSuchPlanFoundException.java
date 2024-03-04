package com.hexaware.careassist.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
@Author :  Yash Dubey
Modified Date : 03-02-2024
Description : Creation of NoSuchPlanFoundException
*/

public class NoSuchPlanFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public NoSuchPlanFoundException(String message) {
		
		super(message);
		
		logger.error(message);
		
	}
}
