package com.luv2code.ppmtool.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.ppmtool.domain.ProjectTask;



@Repository
public interface ProjectTaskRespository extends CrudRepository<ProjectTask, Long>{

	List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);
	
	ProjectTask  findByProjectSequence(String projectSequence);
}
