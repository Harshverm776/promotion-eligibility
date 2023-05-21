package com.cog.hcm.exception;

public class HcmAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = -408231824539448709L;
	private String message;

	public HcmAlreadyExistException(String message) {
		super(message);
		this.message = message;
	}
}
