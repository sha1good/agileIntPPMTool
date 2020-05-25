package com.luv2code.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectTaskIdException  extends RuntimeException{

	public ProjectTaskIdException(String message) {
		super(message);
	}
}
