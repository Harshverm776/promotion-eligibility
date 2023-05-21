package com.cog.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -408231824539448709L;
	private String message;

	public EmployeeNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
