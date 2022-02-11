package com.TaskHunter.project.entity.services;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.TaskHunter.project.entity.dao.ICollectionDao;
import com.TaskHunter.project.entity.models.Collection;
import org.junit.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CollectionServiceImplTest {

	@Autowired
	ICollectionDao CollectionDao;
	
	@Test
	void getAllTest() {
		
		List<Collection> colecciones = (List<Collection>) CollectionDao.findAll();
		Assert.assertEquals(colecciones.size(), 1);
		
	}
	
	@Test
	void insertTest() {

		Collection coleccion = new Collection(2, 1, 0, 0);
		CollectionDao.save(coleccion);
		Collection coleccionGet = CollectionDao.findByIdAppUserAndIdVideoGame((long)2,(long)1);
		Assert.assertEquals(coleccion, coleccionGet);
		CollectionDao.delete(coleccionGet);
		
	}
		
	@Test
	void updateTest() {
		
		Collection coleccion = new Collection(2, 1, 0, 0);
		CollectionDao.save(coleccion);
		Collection coleccionGet = CollectionDao.findByIdAppUserAndIdVideoGame((long)2,(long)1);
		coleccionGet.setGameTime(1);
		Collection comprobacion = CollectionDao.findByIdAppUserAndIdVideoGame((long)2,(long)1);
		int cambio = comprobacion.getGameTime();
		Assert.assertEquals(cambio, 1);
		CollectionDao.delete(comprobacion);
		
	}
	
	@Test
	void deleteTest() {
		
		Collection coleccion = new Collection(2, 1, 0, 0);
		CollectionDao.save(coleccion);
		CollectionDao.delete(coleccion);
		Collection comprobacion = CollectionDao.findByIdAppUserAndIdVideoGame((long)2,(long)1);
		Assert.assertEquals(comprobacion, null);
		
	}
	
	
}
