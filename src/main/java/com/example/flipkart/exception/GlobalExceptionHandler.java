package com.example.flipkart.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.flipkart.service.StudentService;
import com.example.flipkart.util.ProductCategory;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e1){
		logger.error("Invalid resource fetch");
		return new ResponseEntity<String>(e1.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<APIError> handleHttpMessageNotReadableException(
	        HttpMessageNotReadableException ex) {

	    String fieldName = null;
	    String rejectedValue = null;
	    String errorMessage = "Invalid request";

	    Throwable rootCause = ex.getMostSpecificCause();

	    if (rootCause instanceof IllegalArgumentException) {
	        String message = rootCause.getMessage();
	        if (message.contains("Invalid category:")) {
	            fieldName = "category";
	            rejectedValue = message.replace("Invalid category:", "").trim();
	            errorMessage = "Invalid category provided";
	        }
	    }
	    APIError apiError = APIError.builder()
	            .fieldName(fieldName)
	            .rejectedValue(rejectedValue)
	            .errorMessage(errorMessage)
	            .build();

	    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public ResponseEntity<APIError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
//
//	    logger.warn("Validation Problem while adding Product");
//
//	    Throwable throwable = ex;
//
//	    InvalidFormatException invalidFormatEx = null;
//
//	    while (throwable != null) {
//
//	        if (throwable instanceof InvalidFormatException) {
//	            invalidFormatEx = (InvalidFormatException) throwable;
//	            break;
//	        }
//
//	        throwable = throwable.getCause();
//	    }
//
//	    Object rejected = null;
//	    String fieldName = null;
//	    String message = "Invalid Request";
//
//	    if (invalidFormatEx != null) {
//
//	        rejected = invalidFormatEx.getValue();
//
//	        if (!invalidFormatEx.getPath().isEmpty()) {
//	            fieldName = invalidFormatEx.getPath().get(0).getFieldName();
//	        }
//	    }
//
//	    String allowedValues = Arrays.stream(ProductCategory.values())
//	            .map(ProductCategory::getCategory)
//	            .collect(Collectors.joining(", "));
//
//	    APIError error = new APIError(
//	            "Invalid " + fieldName + ", Allowed values [" + allowedValues + "]",
//	            rejected,
//	            message
//	    );
//
//	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	}
}
