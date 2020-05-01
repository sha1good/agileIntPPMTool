package com.luv2code.ppmtool.services;

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

}
