package com.TaskHunter.project.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskHunter.project.entity.dao.IAppUserDao;
import com.TaskHunter.project.entity.dao.ICollectionDao;
import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.Collection;
import com.TaskHunter.project.entity.models.VideoGame;

@Service
public class CollectionServiceImpl implements ICollectionService {

	
	@Autowired
	private ICollectionDao CollectionDao;
	
	@Override
	public List<Collection> getAll() {
		return (List<Collection>) CollectionDao.findAll();
	}

	@Override
	public void insert(Collection collection) {
		
		Collection existingCollection = CollectionDao.findByIdAppUserAndIdVideoGame(collection.getIdAppUser(), collection.getIdVideoGame());
		try {
			if(existingCollection.equals(null)) {
				CollectionDao.save(collection);
			}


		}catch ( Exception e){
			System.out.println("ERROR");
			CollectionDao.save(collection);
		}
		
		
		
	}

	@Override
	public Object findById(Long id) {
		return CollectionDao.findById(id);
	}

	@Override
	public void update(Collection collection) {
		
		Collection existingCollection = CollectionDao.findByIdAppUserAndIdVideoGame(collection.getIdAppUser(), collection.getIdVideoGame());
		

		try {
			if (collection.getGameTime() > 0 ) {

				existingCollection.setGameTime(collection.getGameTime());
			}
		} catch (Exception e) {

		}

		try {

			if (collection.getState() == 0 || collection.getState() == 1 ) {

				existingCollection.setState(collection.getState());
			}
		} catch (Exception e) {

		}
		
		CollectionDao.save(existingCollection);
	}

	@Override
	public void delete(Collection collection) {
		CollectionDao.delete(collection);
		
	}





}
