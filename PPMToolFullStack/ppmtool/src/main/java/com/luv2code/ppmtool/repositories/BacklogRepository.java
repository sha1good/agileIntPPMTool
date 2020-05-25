package com.luv2code.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.ppmtool.domain.Backlog;

@Repository
public interface BacklogRepository  extends CrudRepository<Backlog, Long>{

	Backlog findByProjectIdentifier(String  Identifier);

}
