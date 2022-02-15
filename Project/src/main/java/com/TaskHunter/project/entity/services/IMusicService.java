package com.TaskHunter.project.entity.services;

import com.TaskHunter.project.entity.models.Music;

public interface IMusicService {
	
	public Music MusicBackground(String MusicName);
	public void  InsertMusic(Music music);
	public void  UpdateMusic(Music music, long idMusic);
	public void  DeleteMusic(long idMusic);
	
	
}
