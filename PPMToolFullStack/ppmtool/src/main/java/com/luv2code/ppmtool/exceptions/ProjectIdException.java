package com.luv2code.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectIdException extends RuntimeException {

 public ProjectIdException(String message) {
	 
	 super(message);
	 
 }
	

}
