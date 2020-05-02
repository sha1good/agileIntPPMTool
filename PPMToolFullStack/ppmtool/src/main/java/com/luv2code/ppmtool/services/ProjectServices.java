package com.luv2code.ppmtool.services;

import java.util.Optional;

import org.apache.el.lang.ELArithmetic.LongDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ppmtool.domain.Project;
import com.luv2code.ppmtool.exceptions.ProjectIdException;
import com.luv2code.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectServices {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	 public Project saveOrUpdateProject(Project project) {
		 
		  try {
			   project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			  return projectRepository.save(project);
		  }catch(Exception e) {
			 throw new ProjectIdException("ProjectID '" +project.getProjectIdentifier().toUpperCase() + "' already exist");
		  }
		
	 }
	 
	 
	 public Project findByProjectIdentifier(String projectId) {
		 
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project ==null) {
			 throw new ProjectIdException("ProjectID '" + projectId + "' does not exist");
		}
		
		 return project;
		
	 }
	 
	 public  Iterable<Project> findAllProject(){
		  return projectRepository.findAll();
	 }
	 
	 
	 public void deleteProjectByIdentifier(String projectId) {
		 
		  Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		  if(project ==null) {
			   throw new ProjectIdException("Cannot delete  '"+ projectId + "' that does not exist");
		  }
		  
		   projectRepository.delete(project);
	 }

	 
	 public Optional<Project> findById(Long id) {
		 Optional<Project> project = projectRepository.findById(id);
		 
		 if(project ==null) {
			 throw new ProjectIdException("Project with '" + id + "' does not exist");
		}
		return project;
		
	 }
	 
	 
	 public void delteAllproject() {
	      projectRepository.deleteAll();
	 }
	 
}
