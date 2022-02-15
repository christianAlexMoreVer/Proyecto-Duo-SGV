package com.TaskHunter.project.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.TaskHunter.project.entity.models.Music;

public interface IMusicDao extends CrudRepository<Music, Long>{
	Music findMusicByBackgroundMusic(String MusicName);
}
