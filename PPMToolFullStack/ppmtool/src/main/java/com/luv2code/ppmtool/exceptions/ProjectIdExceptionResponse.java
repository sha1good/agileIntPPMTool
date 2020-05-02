package com.luv2code.ppmtool.exceptions;

import java.sql.Date;

public class ProjectIdExceptionResponse {
	
	private String projectIdentifier;
	//private Long id;
	
	private Date timeStamp;
	private String message;
	private String details;

	public ProjectIdExceptionResponse(String projectIdentifier) {
			this.projectIdentifier = projectIdentifier;
	}

	/*public ProjectIdExceptionResponse(Long id) {
		this.id = id;
	}
	*/
	public ProjectIdExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}


	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

/*	public Long getId() {
		return id;
	}*/

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

/*	public void setId(Long id) {
		this.id = id;
	}
	*/
	

}
