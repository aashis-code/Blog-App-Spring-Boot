package com.SpringApp.BlogApp.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
		String message = resourceNotFoundException.getMessage();
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> errorHash= new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error -> {
		  String field = ((FieldError)error).getField();
		  String defaultMessage = error.getDefaultMessage();
		  errorHash.put(field, defaultMessage);
		});
		
		return new ResponseEntity<>(errorHash,HttpStatus.BAD_REQUEST);
	}
}
