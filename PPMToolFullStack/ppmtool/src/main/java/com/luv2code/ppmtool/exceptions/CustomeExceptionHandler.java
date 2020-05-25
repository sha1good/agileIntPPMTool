package com.luv2code.ppmtool.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomeExceptionHandler  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ProjectIdException.class)
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request){
		
		ProjectIdExceptionResponse  exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
		//ProjectIdExceptionResponse  exceptionResponse=new ProjectIdExceptionResponse(new Date(0), ex.getMessage(),request.getDescription(false));
		 return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(ProjectTaskIdException.class)
	public final ResponseEntity<Object> handleProjectTaskIdException(ProjectTaskIdException ex, WebRequest request){
		ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
		return  new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNameAlreadyExistException.class)
	public final ResponseEntity<Object> handleUsernameAlreadyExistException(UserNameAlreadyExistException ex, WebRequest request){
		UsernameAlreadyExistExceptionResponse exceptionRes = new UsernameAlreadyExistExceptionResponse(ex.getMessage());
		return  new ResponseEntity(exceptionRes, HttpStatus.BAD_REQUEST);
	}
	
/*	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ProjectIdExceptionResponse> handleProjectExceptionId(ProjectIdException ex, WebRequest request){
		ProjectIdExceptionResponse  exceptionResponse = new ProjectIdExceptionResponse(new Date(0), ex.getMessage(),request.getDescription(true));
	
		return new ResponseEntity<ProjectIdExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}*/
	
}	
