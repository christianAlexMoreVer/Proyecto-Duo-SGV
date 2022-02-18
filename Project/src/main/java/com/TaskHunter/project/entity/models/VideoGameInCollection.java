package com.TaskHunter.project.entity.models;

public class VideoGameInCollection extends VideoGame{
	
	public Integer inCollection;
	public Integer inNotCollection;

	public VideoGameInCollection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoGameInCollection(String name, String photo) {
		super(name, photo);
		// TODO Auto-generated constructor stub
	}

	public Integer getInCollection() {
		return inCollection;
	}

	public void setInCollection(Integer inCollection) {
		this.inCollection = inCollection;
	}

	public Integer getInNotCollection() {
		return inNotCollection;
	}

	public void setInNotCollection(Integer inNotCollection) {
		this.inNotCollection = inNotCollection;
	}

}
