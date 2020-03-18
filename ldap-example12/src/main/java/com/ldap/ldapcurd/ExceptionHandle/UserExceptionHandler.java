package com.ldap.ldapcurd.ExceptionHandle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(NotFoundException exc){
		CustomErrorResponse error=new CustomErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<CustomErrorResponse>(HttpStatus.NOT_FOUND);
	}
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
	    public final ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex) 
	    {
		 CustomErrorResponse error=new CustomErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(ex.getMessage());
			error.setTimestamp(System.currentTimeMillis());
	        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	    }
	
}
