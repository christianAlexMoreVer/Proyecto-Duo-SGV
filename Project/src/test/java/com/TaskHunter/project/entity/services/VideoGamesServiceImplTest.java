package com.TaskHunter.project.entity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import com.TaskHunter.project.entity.dao.IVideoGameDao;
import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.VideoGame;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VideoGamesServiceImplTest {

	@Autowired
	IVideoGameDao VideoGameDao;
	
	@Test
	void getAllTest() {
		
		List<VideoGame> juegos = (List<VideoGame>) VideoGameDao.findAll();
		Assert.assertEquals(juegos.size(), 1);
		
	}
	
	@Test
	void insertTest() {
		
		boolean comprobacion;
		VideoGame juego = new VideoGame("Persona test", "img");
		VideoGameDao.save(juego);
		comprobacion = VideoGameDao.findByName("Persona test").isPresent();
		Assert.assertEquals(comprobacion, true);
		VideoGameDao.delete(juego);
		
	}
	
	@Test
	void deleteTest() {
		
		boolean comprobacion;
		VideoGame juego = new VideoGame("Persona test", "img");
		VideoGameDao.save(juego);
		VideoGameDao.delete(juego);
		comprobacion = VideoGameDao.findByName("Persona test").isPresent();
		Assert.assertNotEquals(comprobacion, true);
		
	}
	
	@Test
	void updateTest() {
		boolean comprobacion;
		VideoGame juego = new VideoGame("Persona test", "img");
		VideoGameDao.save(juego);
		VideoGame juegoTest = VideoGameDao.findByName("Persona test").get();
		juegoTest.setName("Cambiado");
		VideoGameDao.save(juegoTest);
		comprobacion = VideoGameDao.findByName("Cambiado").isPresent();
		Assert.assertEquals(comprobacion, true);
		VideoGameDao.delete(juegoTest);
	}
	
	@Test
	void findByIdTest() {
		
		boolean comprobacion = VideoGameDao.findById((long) 1).isPresent();
		Assert.assertEquals(comprobacion, true);
		
	}
	
	@Test
	void findVideoGameByNameContainingTest() {
		
		List<VideoGame> comprobacion = VideoGameDao.findVideoGameByNameContaining("Persona");
		Assert.assertEquals(comprobacion.size(), 1);
		
	}
	
	@Test
	void findByNameTest() {
		boolean comprobacion = VideoGameDao.findByName("Persona 3").isPresent();
		Assert.assertEquals(comprobacion, true);
	}
}
