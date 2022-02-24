package com.TaskHunter.project.entity.services;

import com.TaskHunter.project.entity.models.Music;

import java.util.List;

public interface IMusicService {

	public List<Music> getAll();
	public Music MusicBackgroundByName(String MusicName);
	public Music MusicBackgroundByInUse();
	public boolean findByMusicBackground(String MusicName);
	public void  InsertMusic(Music music);
	public void  UpdateMusic(Music music, long idMusic);
	public void  DeleteMusic(long idMusic);

	
	
}
