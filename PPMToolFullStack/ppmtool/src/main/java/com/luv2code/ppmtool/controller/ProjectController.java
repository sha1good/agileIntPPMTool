package com.luv2code.ppmtool.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ppmtool.domain.Project;
import com.luv2code.ppmtool.exceptions.ProjectIdException;
import com.luv2code.ppmtool.services.MapValidationErrorService;
import com.luv2code.ppmtool.services.ProjectServices;

import net.bytebuddy.asm.Advice.Return;
import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
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
	
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		
		Project project = projectService.findByProjectIdentifier(projectId);
		
		 return new ResponseEntity<Project>(project, HttpStatus.OK);
		 
		 
	}
	
  @GetMapping("/all")
  public Iterable<Project> findAllProject(){
	   return projectService.findAllProject();
  }
	
	
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
	  projectService.deleteProjectByIdentifier(projectId);
	 return new ResponseEntity<String>("Proejct with ID : '" + projectId + "' was deleted successfully.", HttpStatus.OK);	 
	}
	
	
	@PutMapping("/{id}")
	 public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project project){
		
		
		//Long  Proid = Long.parseLong(id);
		
		Optional<Project> projectData = projectService.findById(id);
		
		if(!projectData.isPresent()) {
			 throw new ProjectIdException("Id  -" + id  + " was not found"); 
		}
		if(projectData.isPresent()) {
			Project projectGotten = projectData.get(); // will be retreived from the DB
			projectGotten.setProjectName(project.getProjectName());
			projectGotten.setDescription(project.getDescription());
			projectGotten.setProjectIdentifier(project.getProjectIdentifier());
			
			projectService.saveOrUpdateProject(projectGotten);
			
			return new  ResponseEntity<String>("Your Project with '" + id + "' was successfully updated!", HttpStatus.OK);
		}else {
			 return  new ResponseEntity<String>("Your Project with '" + id + "' was not found!",HttpStatus.NOT_FOUND);
			
		}
		
	
}
	
	@DeleteMapping("/all")
	public  ResponseEntity<?> deleteAllProjects(){
		
	try {
		projectService.delteAllproject();
		return new ResponseEntity<String>("All project has been successfully deleted", HttpStatus.OK);
	
     }catch (Exception e) {
	  return new ResponseEntity<String>("Project was unable to be deleted! ", HttpStatus.EXPECTATION_FAILED);
  }
}
	
	
	
}	
