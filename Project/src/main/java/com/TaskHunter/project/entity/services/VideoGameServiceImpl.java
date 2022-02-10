package com.TaskHunter.project.entity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskHunter.project.entity.dao.IVideoGameDao;
import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.VideoGame;

@Service
public class VideoGameServiceImpl implements IVideoGameService {

	@Autowired
	private IVideoGameDao VideoGameDao;
	
	@Override
	public List<VideoGame> getAll() {
		
		return (List<VideoGame>) VideoGameDao.findAll();
	}

	@Override
	public void insert(VideoGame VideoGame) {
		VideoGameDao.save(VideoGame);
		
	}

	@Override
	public void delete(long id) {
		VideoGameDao.deleteById(id);
		
	}

	@Override
	public void update(VideoGame videogame, long id) {
		if (VideoGameDao.findById(id).isPresent()) {
			VideoGame existingGame = VideoGameDao.findById(id).get();
			
			System.out.println(videogame.getName());

			try {
				if (videogame.getName().length() != 0) {
					existingGame.setName(videogame.getName());
				}

			} catch (Exception e) {

			}


			try {

				if (!videogame.getPhoto().equals(null)) {

					existingGame.setPhoto(videogame.getPhoto());
				}
			} catch (Exception e) {

			}

			VideoGameDao.save(existingGame);
		}
		
	}

	@Override
	public Optional<VideoGame> findById(Long id) {
		
		return VideoGameDao.findById(id);
		
	}

	@Override
	public List<VideoGame> findVideoGameByNameContaining(String Name) {
		
		return VideoGameDao.findVideoGameByNameContaining(Name);
	}

	@Override
	public boolean findByName(String Name) {
		
		return VideoGameDao.findByName(Name).isPresent();
	}

}
