package com.example.realEstateGo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Handle validation errors and return them as a map of field names and error
		// messages
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			map.put(fieldName, msg);
		});
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		// Handle resource not found exception and return a custom message
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get Resources");
		String msg = String.format("%s not found with %s: '%s'", ex.getResourceName(), ex.getFieldName(),
				ex.getFieldValue());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)

	public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {

		// Handle resource already exists exception and return a custom message
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get User");
		return ResponseEntity.status(HttpStatus.IM_USED).headers(headers).body(ex.getMessage());

	}

}
