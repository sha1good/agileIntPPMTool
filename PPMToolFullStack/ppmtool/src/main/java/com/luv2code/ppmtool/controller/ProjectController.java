package com.luv2code.ppmtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ppmtool.domain.Project;
import com.luv2code.ppmtool.services.ProjectServices;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectServices projectService;
	
	@PostMapping("")
	public ResponseEntity<Project> createNewProject(@RequestBody Project project){
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}
	
}
