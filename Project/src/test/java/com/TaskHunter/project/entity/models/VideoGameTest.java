package com.TaskHunter.project.entity.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.TaskHunter.project.entity.dao.IVideoGameDao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VideoGameTest {
	
	@Autowired
	IVideoGameDao videoGameDao;
	
	@Test
	void getIdVideoGameTest() {
		
		VideoGame test = (videoGameDao.findVideoGameByNameContaining("Persona 3")).get(0);
		Assert.assertEquals(test.getIdVideoGame(), 1);
		
	}
	
	@Test
	void setIdVideoGameTest() {
		
		VideoGame test = (videoGameDao.findVideoGameByNameContaining("Persona 3")).get(0);
		test.setIdVideoGame(10);
		Assert.assertEquals(test.getIdVideoGame(), 10);
		
	}
	
	@Test
	void getNameTest() {
		
		VideoGame test = (videoGameDao.findVideoGameByNameContaining("Persona 3")).get(0);
		Assert.assertEquals(test.getName(), "Persona 3");
		
	}
	
	@Test
	void setNameTest() {
		
		VideoGame test = (videoGameDao.findVideoGameByNameContaining("Persona 3")).get(0);
		test.setName("Test");
		Assert.assertEquals(test.getName(), "Test");
		
	}
	
	@Test
	void getPhotoTest() {
		
		VideoGame test = (videoGameDao.findVideoGameByNameContaining("Persona 3")).get(0);
		Assert.assertEquals(test.getPhoto(), null);
		
	}
	
	@Test
	void setPhotoTest() {
		
		VideoGame test = (videoGameDao.findVideoGameByNameContaining("Persona 3")).get(0);
		test.setPhoto("Test.jpg");
		Assert.assertEquals(test.getPhoto(), "Test.jpg");
		
	}
	
}
