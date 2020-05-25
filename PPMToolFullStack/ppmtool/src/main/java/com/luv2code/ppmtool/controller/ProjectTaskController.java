package com.luv2code.ppmtool.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ppmtool.domain.Backlog;
import com.luv2code.ppmtool.domain.ProjectTask;
import com.luv2code.ppmtool.services.MapValidationErrorService;
import com.luv2code.ppmtool.services.ProjectTaskService;



@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class ProjectTaskController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/{projectIdentifer}")
	public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask, 
			                BindingResult result, @PathVariable String  projectIdentifer,Principal principal){
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		if(errorMap !=null) return errorMap;
		
		ProjectTask projectTask1 =  projectTaskService.addProjectTask(projectIdentifer, projectTask,principal.getName());
		
	 return new ResponseEntity<ProjectTask> (projectTask1, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<List<ProjectTask>> getProjectTaskFromBackLog(@PathVariable String projectIdentifier,Principal principal){
		
		List<ProjectTask> projectTask = projectTaskService.findProjectTaskFromBackLogById(projectIdentifier,principal.getName());
		
		return new ResponseEntity<List<ProjectTask>>(projectTask, HttpStatus.OK);
	}

	
	@GetMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<ProjectTask> getPTByProjectSequence(@PathVariable String projectIdentifier,
			@PathVariable String projectSequence,Principal principal ){
		ProjectTask projectTask = projectTaskService.findPTByProjectSequence(projectIdentifier,projectSequence,principal.getName());
		
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	
	@PatchMapping("/{projectIdentifier}/{projectSequence}")
	 public ResponseEntity<?>  updateProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,
			                                     @PathVariable String projectIdentifier, @PathVariable String projectSequence,Principal principal){
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
		 if(errorMap !=null) return errorMap;
		
		 ProjectTask updatedProjectTask= projectTaskService.updatedProjectTask(projectTask, projectIdentifier, projectSequence,principal.getName());
		 
	 return new ResponseEntity<ProjectTask>(updatedProjectTask, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{projectIdentifier}/{projectSequence}")
	 public ResponseEntity<?> deleteProjectTask(@PathVariable String projectIdentifier, @PathVariable String projectSequence,Principal principal){
		
		 
		
		String projectTask = projectTaskService.deleteProjectTaskByProjectSequence(projectIdentifier, projectSequence,principal.getName());
		
		return new ResponseEntity<String>(projectTask, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
