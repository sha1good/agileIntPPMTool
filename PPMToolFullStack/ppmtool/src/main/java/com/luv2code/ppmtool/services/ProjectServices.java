package com.luv2code.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ppmtool.domain.Project;
import com.luv2code.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectServices {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	 public Project saveOrUpdateProject(Project project) {
		 
		 return projectRepository.save(project);
	 }

}
