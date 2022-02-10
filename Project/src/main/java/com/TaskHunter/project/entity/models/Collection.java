package com.TaskHunter.project.entity.models;

import javax.persistence.*;

@Entity
@Table(name = "Collection")
@IdClass(CollectionPK.class)
public class Collection {
    private long idAppUser;
    private long idVideoGame;
    private int State;
    private int GameTime ;
    
    
    
    public Collection() {

	}

	
	public Collection(long idAppUser, long idVideoGame, int state, int gameTime) {
		super();
		this.idAppUser = idAppUser;
		this.idVideoGame = idVideoGame;
		this.State = state;
		this.GameTime = gameTime;
	}
	
	
	@Id
    @Column(name = "idAppUser", nullable = false)
    public long getIdAppUser() {
        return idAppUser;
    }

    public void setIdAppUser(long idAppUser) {
        this.idAppUser = idAppUser;
    }

    @Id
    @Column(name = "idVideoGame", nullable = false)
    public long getIdVideoGame() {
        return idVideoGame;
    }

    public void setIdVideoGame(long idVideoGame) {
        this.idVideoGame = idVideoGame;
    }
    
    @Column(name = "State", nullable = false)
    public int getState() {
        return State;
    }
    
    @Column(name = "GameTime", nullable = false)
    public int getGameTime() {
        return GameTime;
    }
    
    

    public void setState(int state) {
		State = state;
	}



	public void setGameTime(int gameTime) {
		GameTime = gameTime;
	}



	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collection that = (Collection) o;

        if (idAppUser != that.idAppUser) return false;
        if (idVideoGame != that.idVideoGame) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idAppUser;
        result = (int) (31 * result + idVideoGame);
        return result;
    }
}