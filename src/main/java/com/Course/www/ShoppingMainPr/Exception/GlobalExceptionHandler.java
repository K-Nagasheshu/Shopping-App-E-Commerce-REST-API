package com.Course.www.ShoppingMainPr.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Course.www.ShoppingMainPr.Main.ErrorDetails;
import com.Course.www.ShoppingMainPr.Main.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { 
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity <ErrorDetails> handleAllException(UserNotFoundException ex, WebRequest request) throws Exception {
		
		ErrorDetails error = new ErrorDetails(ex.getMessage());
		
		return new ResponseEntity(error,HttpStatus.BAD_GATEWAY);
		
	}
	
	@Override
	protected ResponseEntity <Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
			HttpStatusCode Status, WebRequest request) {
		
		String Message = ex.getBindingResult()
				.getFieldError()
				.getDefaultMessage();
		
		ErrorDetails error = new ErrorDetails(Message);
		
		
		return new ResponseEntity(error,HttpStatus.NOT_FOUND);
		
	}

}
