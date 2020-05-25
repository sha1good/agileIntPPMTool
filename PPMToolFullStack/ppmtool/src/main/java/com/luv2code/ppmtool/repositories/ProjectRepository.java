package com.luv2code.ppmtool.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
	
  /*  @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);*/
	
	Project findByProjectIdentifier(String projectId);

	@Override
	 Iterable<Project> findAll();

	Iterable<Project> findByProjectLeader(String username);
	
	 Optional<Project>  findById(Long id);

	
	
   
	
	
}
