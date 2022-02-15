package com.TaskHunter.project.entity.models;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.Assert;
import com.TaskHunter.project.entity.dao.ICollectionDao;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CollectionTest {
	
	@Autowired
	ICollectionDao collectionDao;
	
	@Test
	void getIdAppUserTest() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		Assert.assertEquals(test.getIdAppUser(), 1);
		
	}
	
	@Test
	void setIdAppUserTest() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		test.setIdAppUser(10);
		Assert.assertEquals(test.getIdAppUser(), 10);
		
	}
	
	@Test
	void getIdVideoGameTest() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		Assert.assertEquals(test.getIdVideoGame(), 1);
		
	}
	
	@Test
	void setIdVideoGameTest() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		test.setIdVideoGame(10);
		Assert.assertEquals(test.getIdVideoGame(), 10);
		
	}
	
	@Test
	void getStateTest() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		Assert.assertEquals(test.getState(), 1);
		
	}
	
	@Test
	void getGameTimeTest() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		Assert.assertEquals(test.getGameTime(), 30);
		
	}
	
	@Test
	void setStateTest() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		test.setState(0);
		Assert.assertEquals(test.getState(), 0);
	}
	
	@Test
	void setGameTime() {
		
		Collection test = (collectionDao.findByIdAppUser((long)1)).get(0);
		test.setGameTime(40);
		Assert.assertEquals(test.getGameTime(), 40);
		
	}
	
}
