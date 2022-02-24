package com.TaskHunter.project.entity.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Music")
public class Music implements Serializable{

	@Id
    @Column(name = "idMusic", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMusic;
	
	private String backgroundMusic;
	private int inUse;

	public Music(String backgroundMusic) {
		super();
		this.backgroundMusic = backgroundMusic;
	}

	public Music() {}

    public Music(String musicBackground, int inUse) {
		super();
		this.backgroundMusic = musicBackground;
		this.inUse = inUse;
    }


    public String getBackgroundMusic() {
		return backgroundMusic;
	}

	public void setBackgroundMusic(String backgroundMusic) {
		backgroundMusic = backgroundMusic;
	}

	public long getIdMusic() {
		return idMusic;
	}

	public void setIdMusic(long idMusic) {
		this.idMusic = idMusic;
	}

	public int getInUse() {
		return inUse;
	}

	public void setInUse(int inUse) {
		this.inUse = inUse;
	}


}
