package it.epicode.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<Object> handleCustomException(CustomException ex) {
		CustomError customError = new CustomError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return buildResponseEntity(customError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(CustomError ce) {
		return new ResponseEntity<>(ce, ce.getStatus());
	}

}
