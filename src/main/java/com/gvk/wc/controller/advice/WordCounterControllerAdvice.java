package com.gvk.wc.controller.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class WordCounterControllerAdvice {
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  public ErrorMessage constraintViolation(ConstraintViolationException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getLocalizedMessage());
	    return message;
	  }

}
