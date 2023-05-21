package com.cog.employee.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cog.employee.domain.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ResponseEntity<ErrorResponse> employeeNotFoundException(Exception ex, HttpServletRequest request) {
		
		return new ResponseEntity<>(
				ErrorResponse.builder()
				.errorMessage(ex.getLocalizedMessage()) 
	            .errorCode(HttpStatus.NOT_FOUND.toString())
	            .request(request.getRequestURI())
	            .requestType(request.getMethod())
	            .customMessage(ex.getMessage())
	            .build(), HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public Map<String, String> validationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
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
