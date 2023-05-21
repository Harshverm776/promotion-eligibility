package com.cog.employee.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

	private String errorMessage;
	private String errorCode;
	private String request;
	private String requestType;
	private String customMessage;
}
