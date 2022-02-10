package com.TaskHunter.project.entity.models;



import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "VideoGame")
public class VideoGame {
	
    @Id
    @Column(name = "idVideoGame", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVideoGame;
    
    private String name;

    private String photo;
    
    public VideoGame() {
    	
    }
    
    public VideoGame(String name, String photo) {
    	super();
    	this.name = name;
		this.photo = photo;
    	
    }

    public long getIdVideoGame() {
        return idVideoGame;
    }

    public void setIdVideoGame(long idVideoGame) {
        this.idVideoGame = idVideoGame;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 70)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Basic
    @Column(name = "Photo", nullable = true)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoGame that = (VideoGame) o;

        if (idVideoGame != that.idVideoGame) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        

        return true;
    }

    @Override
    public int hashCode() {
        long result = idVideoGame;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        
        return (int) result;
    }
}
