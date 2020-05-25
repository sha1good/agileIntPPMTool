package com.luv2code.ppmtool.exceptions;

public class UsernameAlreadyExistExceptionResponse {

	private String username;

	public UsernameAlreadyExistExceptionResponse(String username) {
	
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
