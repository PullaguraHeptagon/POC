package com.student.info.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.student.info.model.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class EntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorMessage> studentNotFoundException(StudentNotFoundException e, WebRequest request)
	{
		ErrorMessage mesg= new ErrorMessage(HttpStatus.NOT_FOUND,e.getMessage());
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mesg);
	}
}
