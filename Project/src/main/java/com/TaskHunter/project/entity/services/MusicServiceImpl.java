package com.TaskHunter.project.entity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskHunter.project.entity.dao.IMusicDao;
import com.TaskHunter.project.entity.models.Music;

import java.util.List;

@Service
public class MusicServiceImpl implements IMusicService {
	
	@Autowired
	private IMusicDao MusicDao;

	@Override
	public List<Music> getAll() {
		return (List<Music>) MusicDao.findAll();
	}

	@Override
	public Music MusicBackgroundByName(String MusicName) {
		return MusicDao.findMusicByBackgroundMusic(MusicName);
	}

	@Override
	public Music MusicBackgroundByInUse() {
		return MusicDao.findMusicByInUse(1);
	}

	@Override
	public boolean findByMusicBackground(String MusicName) {
		if (MusicName.equals(MusicDao.findMusicByBackgroundMusic(MusicName).getBackgroundMusic()) == true){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void InsertMusic(Music music) {
		MusicDao.save(music);
	}

	@Override
	public void UpdateMusic(Music music, long idMusic) {
		MusicDao.findById(idMusic).ifPresent((x)->{
			music.setIdMusic(idMusic);
			MusicDao.save(music);
		});
	}

	@Override
	public void DeleteMusic(long idMusic) {
		MusicDao.deleteById(idMusic);
	}

	

}
