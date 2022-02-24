package com.TaskHunter.project.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.TaskHunter.project.entity.models.Music;

import java.util.List;

public interface IMusicDao extends CrudRepository<Music, Long>{

	List<Music> findMusicByBackgroundMusicContaining(String MusicName);
	Music findMusicByBackgroundMusic(String MusicName);
	Music findMusicByInUse(int valor);
	Music findMusicByIdMusic(long id);
}
