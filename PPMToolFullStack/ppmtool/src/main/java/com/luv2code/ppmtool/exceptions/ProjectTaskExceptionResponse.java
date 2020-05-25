package com.luv2code.ppmtool.exceptions;


public class ProjectTaskExceptionResponse {
	
	private String projectIdentifier;
	
/*	private Date timestamp;
	private String message;
	
	private String details;
*/
		
	public ProjectTaskExceptionResponse() {
		super();
	}

	public ProjectTaskExceptionResponse(String projectIdentifier) {
		super();
		this.projectIdentifier = projectIdentifier;
	}

/*	public ProjectTaskExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}*/

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	
	

}
