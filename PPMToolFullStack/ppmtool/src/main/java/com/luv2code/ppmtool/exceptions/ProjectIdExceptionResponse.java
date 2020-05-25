package com.luv2code.ppmtool.exceptions;



public class ProjectIdExceptionResponse {
	
	private String projectIdentifier;
	//private Long id;
	
/*	private Date timeStamp;
	private String message;
	private String details;*/

	public ProjectIdExceptionResponse(String projectIdentifier) {
			this.projectIdentifier = projectIdentifier;
	}

	/*public ProjectIdExceptionResponse(Long id) {
		this.id = id;
	}
	*/
/*	public ProjectIdExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}*/


	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

/*	public Long getId() {
		return id;
	}*/

	



/*	public void setId(Long id) {
		this.id = id;
	}
	*/
	

}
