package com.TaskHunter.project.entity.services;

import java.util.List;

import com.TaskHunter.project.entity.models.Collection;
import com.TaskHunter.project.entity.models.VideoGame;

import ch.qos.logback.core.joran.spi.ActionException;

public interface ICollectionService {

	public List<Collection> getAll();
	void insert(Collection collection);
	public Object findById(Long id);
	public void update(Collection collection);
	public void delete(Collection collection);
	
}
