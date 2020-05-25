package com.luv2code.ppmtool.services;

import java.util.List;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ppmtool.domain.Backlog;
import com.luv2code.ppmtool.domain.Project;
import com.luv2code.ppmtool.domain.ProjectTask;
import com.luv2code.ppmtool.exceptions.ProjectIdExceptionResponse;
import com.luv2code.ppmtool.exceptions.ProjectTaskIdException;
import com.luv2code.ppmtool.repositories.BacklogRepository;
import com.luv2code.ppmtool.repositories.ProjectRepository;
import com.luv2code.ppmtool.repositories.ProjectTaskRespository;

@Service
public class ProjectTaskService {
	
	@Autowired
	BacklogRepository backlogRepository;
	
	@Autowired
	ProjectTaskRespository projectTaskRespository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	private ProjectServices projectServices;
	
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask,String username) {
		
		//try {
			//Project Tasks must be added to a specific Project  i.e project !=null . BackLog Must Exist
		
			Backlog backlog =  projectServices.findByProjectIdentifier(projectIdentifier, username).getBacklog();//backlogRepository.findByProjectIdentifier(projectIdentifier);
			
			if(backlog ==null) {
				 throw new ProjectTaskIdException("Project with '" + projectIdentifier +"' Not Found In Your Account");
			}
			 System.out.println(" This is my Backlog " + backlog);
			
			//set the  project task to backlog
			projectTask.setBacklog(backlog);
			
			//We want our project sequence to be like this, but you have to get  the projectSequence : (PROJECTIDENTIFIER+PTSEQUENCE) ..TEST1-1,TEST1-2  e.t.c
	         Integer ptSequence = backlog.getPTSequence();
	         
			//Also updating the backlog PTSequence so  that, we know we have the right sequeuce at the backlog
	         ptSequence++;
	         backlog.setPTSequence(ptSequence);
	         //Add the project sequnce to  the project task
	         projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + ptSequence);
	         projectTask.setProjectIdentifier(projectIdentifier);
	         
			// Also set the  priority of the task if it == null(LOW,MEDIUM,HIGH)
	         /*if(projectTask.getPriority() ==0 || projectTask.getPriority() ==null) {
	        	 projectTask.setPriority(3); 
	        	 will be treated in the future cos we need projectTask.getPriority() ==0  to handle the form from the react side
	         }*/
	         
	         if(projectTask.getPriority() ==null||projectTask.getPriority() ==0) {
	        	 projectTask.setPriority(3);
	         }
	         
			//Also set the status if the status ofis null("TODO" "DONE") our task
			if(projectTask.getStatus() =="" || projectTask.getStatus() ==null) {
				projectTask.setStatus("TO_DO");
			}
			
			//  DO NOT FORGET TO HANDLE THE EXCEPTION
			
			
			return projectTaskRespository.save(projectTask);
			
			
		} 
	
	
	/*catch (Exception e) {
			  throw  new ProjectTaskIdException("ProjectId with '" + projectIdentifier.toUpperCase()+ "'  Not Found");
		}*/
		
	

	public List<ProjectTask> findProjectTaskFromBackLogById(String projectIdentifier,String username) {
		
	//List<ProjectTask> projectTask = projectTaskRespository.findByProjectIdentifierOrderByPriority(projectIdentifier);
	/*	Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
		if(project == null) {
			throw new ProjectTaskIdException("Project with '" + projectIdentifier.toUpperCase() +"' Does not Exist");
		}*/
		
		projectServices.findByProjectIdentifier(projectIdentifier, username);
		
		return projectTaskRespository.findByProjectIdentifierOrderByPriority(projectIdentifier);
	}

	
	public ProjectTask findPTByProjectSequence(String projectIdentifier,String projectSequence,String username) {
		
		//We  make sure that we seaarching on existing backlog
	/*	
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		if(backlog ==null) {
			throw new ProjectTaskIdException("Project with '" + projectIdentifier.toUpperCase() +"' Does not Exist");
		}*/
		
		projectServices.findByProjectIdentifier(projectIdentifier, username);
		
		//make sure  that our task exist
       ProjectTask projectTask = projectTaskRespository.findByProjectSequence(projectSequence);
       if(projectTask ==null) {
    	   throw new ProjectTaskIdException("ProjectTask  with '" + projectSequence +"' Does not Exist");
       }
		
		//make sure that the  task and the project are conform , not be able to find task by  passing the wrong  project(e.g COP123 != COP124)
       if(!projectTask.getProjectIdentifier().equals(projectIdentifier)) {
    	    throw new ProjectTaskIdException("Project Task with '" + projectSequence + "' does not exist in project : " + projectIdentifier);
       }
		
		  return  projectTask;
	}

   public ProjectTask updatedProjectTask(ProjectTask updatedProjectTask, String projectIdentifier, String projectSequence,String username) {
	   
	 //  ProjectTask projectTask = projectTaskRespository.findByProjectSequence(projectSequence);
	   ProjectTask projectTask = findPTByProjectSequence(projectIdentifier,projectSequence,username);
	   projectTask = updatedProjectTask;
	
	   
	   return projectTaskRespository.save(projectTask);
	   
   }
   
   
   public String deleteProjectTaskByProjectSequence(String projectIdentifier, String projectSequence,String username) {
	   ProjectTask projectTask = findPTByProjectSequence(projectIdentifier,projectSequence,username);
	   
	 /*   Backlog backlog = projectTask.getBacklog();
	   	List<ProjectTask> projectTasks = backlog.getProjectTasks();
	    projectTasks.remove(projectTask);
	    
	    backlogRepository.save(backlog);*/
	    
	   projectTaskRespository.delete(projectTask);
	    
	    return "Project task has been successfully deleted";
   }
   
   
   
}
