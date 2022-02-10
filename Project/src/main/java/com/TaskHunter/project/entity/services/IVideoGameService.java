package com.TaskHunter.project.entity.services;

import java.util.List;
import java.util.Optional;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.VideoGame;

public interface IVideoGameService {

	public List<VideoGame> getAll();
	void insert(VideoGame VideoGame);
	void delete(long id);
	void update(VideoGame VideoGame, long id);
	public Optional<VideoGame> findById(Long id);
	public List<VideoGame> findVideoGameByNameContaining(String Name);
	public boolean findByName(String Name);
}
