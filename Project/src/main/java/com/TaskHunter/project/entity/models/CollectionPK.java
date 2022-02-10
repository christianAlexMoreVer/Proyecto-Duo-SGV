package com.TaskHunter.project.entity.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CollectionPK implements Serializable {
    private long idAppUser;
    private long idVideoGame;

    @Column(name = "idAppUser", nullable = false)
    @Id
    public long getIdAppUser() {
        return idAppUser;
    }

    public void setIdAppUser(long idAppUser) {
        this.idAppUser = idAppUser;
    }

    @Column(name = "idVideoGame", nullable = false)
    @Id
    public long getIdVideoGame() {
        return idVideoGame;
    }

    public void setIdVideoGame(long idVideoGame) {
        this.idVideoGame = idVideoGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionPK that = (CollectionPK) o;

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
