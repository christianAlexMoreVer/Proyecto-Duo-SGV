package com.TaskHunter.project.entity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.Collection;

public interface ICollectionDao extends CrudRepository<Collection, Long> {
	List<Collection> findByIdAppUser(Long idAppUser);
	Collection findByIdAppUserAndIdVideoGame(Long idAppUser, Long idVideoGame);
}