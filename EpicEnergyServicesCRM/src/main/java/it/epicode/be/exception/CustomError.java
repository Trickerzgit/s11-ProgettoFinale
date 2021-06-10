package it.epicode.be.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CustomError {

	private String message;
	private HttpStatus status;
	
	public CustomError(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}
	
}
