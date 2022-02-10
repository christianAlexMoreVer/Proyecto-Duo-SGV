package com.TaskHunter.project.entity.models;


import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "AppUser")
public  class AppUser implements Serializable{
	
    @Id
    @Column(name = "idAppUser", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAppUser;
    
    private String email;
    private String password;
    private String userName;
    private String photo;
    private int rol;
    

    public long getIdAppUser() {
        return idAppUser;
    }

    
    public AppUser( String email, String password, String userName, int rol) {
		super();
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.rol = rol;
	}
    
    public AppUser(String email, String password, String userName, String photo) {
    	super();
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.photo = photo;
	}
    
    public AppUser(String email, String password, String userName) {
    	super();
		this.email = email;
		this.password = password;
		this.userName = userName;
	}

    public AppUser(String photo) {
    	super();
    	this.photo = photo;
	}
    
    public AppUser() {

   	}
    

    @Column(name = "Rol", nullable = false)
	public int getRol() {
		return rol;
	}



	public void setRol(int rol) {
		this.rol = rol;
	}



	public void setIdAppUser(long idAppUser) {
        this.idAppUser = idAppUser;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 70)
    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

  
    @Basic
    @Column(name = "Password", nullable = false, length = 250)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 45)
    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "photo", nullable = true)
    public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser that = (AppUser) o;

        if (idAppUser != that.idAppUser) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;

        return true;
    }


}
