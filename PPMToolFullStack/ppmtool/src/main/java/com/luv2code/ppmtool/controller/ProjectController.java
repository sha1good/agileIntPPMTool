package com.luv2code.ppmtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ppmtool.domain.Project;
import com.luv2code.ppmtool.services.MapValidationErrorService;
import com.luv2code.ppmtool.services.ProjectServices;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectServices projectService;
	
	@Autowired
	private MapValidationErrorService  mapValidationErrorService;
	//Binding Result helps us to Bind our Object with the validation that was setup
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
	ResponseEntity<?> errorMap =mapValidationErrorService.MapValidationError(result);
	if(errorMap !=null) return errorMap;
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}
	
}
