package com.cog.promotion.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cog.promotion.domain.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorResponse> genericException(Exception ex, HttpServletRequest request) {
		return new ResponseEntity<>(
				ErrorResponse.builder()
				.errorMessage(ex.getLocalizedMessage()) 
	            .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
	            .request(request.getRequestURI())
	            .requestType(request.getMethod())
	            .customMessage("Something went wrong.")
	            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
