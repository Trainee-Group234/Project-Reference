package com.yash.ppm.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<?> handleValidationExceptions(ConstraintViolationException e){
		Map<String, String> errors = new HashMap<>();
		e.getConstraintViolations().stream().forEach((error) -> {
	        String fieldName = error.getPropertyPath().toString();
	        String errorMessage = error.getMessage();
	        errors.put(fieldName, errorMessage);
	    });

			
		return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(errors);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(errors);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityException(DataIntegrityViolationException ex){
		return new ResponseEntity<>("Project Identifier must be unique",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElement() {
		return new ResponseEntity<>("No Project Found for the corresping ID",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProjectIdentifierException.class)
	public ResponseEntity<?> handleNoIdentifier(ProjectIdentifierException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
}
