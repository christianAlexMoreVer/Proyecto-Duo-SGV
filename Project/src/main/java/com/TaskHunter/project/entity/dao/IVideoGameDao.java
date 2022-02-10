package com.TaskHunter.project.entity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.Collection;
import com.TaskHunter.project.entity.models.VideoGame;


public interface IVideoGameDao extends CrudRepository<VideoGame, Long> {
	List<VideoGame> findVideoGameByNameContaining(String Name);
	Optional<VideoGame> findByName(String Name);
}
