package com.luv2code.ppmtool.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ppmtool.domain.Backlog;
import com.luv2code.ppmtool.domain.Project;
import com.luv2code.ppmtool.domain.User;
import com.luv2code.ppmtool.exceptions.ProjectIdException;
import com.luv2code.ppmtool.repositories.BacklogRepository;
import com.luv2code.ppmtool.repositories.ProjectRepository;
import com.luv2code.ppmtool.repositories.UserRepository;

@Service
public class ProjectServices {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	 public Project saveOrUpdateProject(Project project,String username) {
		 
		 User  user  = userRepository.findByUsername(username);
		  project.setUser(user);
		  project.setProjectLeader(user.getUsername());
		  
		  if(project.getId() !=null) {
			  Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			  if(existingProject !=null && (!existingProject.getProjectLeader().equals(username))) {
				   throw new ProjectIdException("Project Not Found In Your Account !");
			  }else if(existingProject == null) {
				  throw new ProjectIdException("Project with ID: '" + project.getProjectIdentifier() +"' cannot be updated,because it does not exist!");
			  }
		  }
		  
		  try {
			   project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		/*Be Care here. While Creating a new Project, the project Id will be null , but while updating the project ,
		the  projectId will contain a value. So while creating a new Project, we can  create a new backlog with it  
			   */
			   if(project.getId() == null) {
				   Backlog backlog = new Backlog();
				   project.setBacklog(backlog);
				   backlog.setProject(project);
				   backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			   }else {
				   if(project.getId() !=null) {
					   project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
				   }
			   }
			  return projectRepository.save(project);
		  }catch(Exception e) {
			 throw new ProjectIdException("ProjectID '" +project.getProjectIdentifier().toUpperCase() + "' already exist");
		  }
		
	 }
	 
	 
	 public Project findByProjectIdentifier(String projectId,String username) {
		 
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project ==null) {
			 throw new ProjectIdException("ProjectID '" + projectId + "' does not exist");
		}
		
		if(!project.getProjectLeader().equals(username)) {
			throw new ProjectIdException("Project Not found in your account !");
		}
		
		 return project;
		
	 }
	 
	 public  Iterable<Project> findAllProject(String username){
		 		 
		  return projectRepository.findByProjectLeader(username);
	 }
	 
	 
	 public void deleteProjectByIdentifier(String projectId,String username) {
		 	 
		  
		   projectRepository.delete(findByProjectIdentifier(projectId, username));
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
