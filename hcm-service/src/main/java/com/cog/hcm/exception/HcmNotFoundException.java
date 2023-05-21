package com.cog.hcm.exception;

public class HcmNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -408231824539448709L;
	private String message;

	public HcmNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
