package com.TaskHunter.project.entity.models;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.Assert;
import com.TaskHunter.project.entity.dao.IAppUserDao;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppUserTest {
	
	@Autowired
    IAppUserDao AppUserDao;
	
	@Test
	void getIdAppUserTest() {

		AppUser test = (AppUserDao.findById((long)1)).get();
		Assert.assertEquals(test.getIdAppUser(), 1);
		
	}
	
	@Test
	void getRolTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		Assert.assertEquals(test.getRol(), 1);
		
	}
	
	@Test
	void setRolTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		test.setRol(0);
		Assert.assertEquals(test.getRol(), 0);
		
	}
	
	@Test
	void setIdAppUserTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		test.setIdAppUser(20);
		Assert.assertEquals(test.getIdAppUser(), 20);
		
	}
	
	@Test
	void getemailTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		Assert.assertEquals(test.getemail(), "loloeladmin@gmail.com");
		
	}
	
	@Test
	void setemailTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		test.setemail("test@gmail.com");
		Assert.assertEquals(test.getemail(),"test@gmail.com");
		
	}
	
	@Test
	void getPasswordTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		Assert.assertEquals(test.getPassword(),"$2a$10$XYO5VIjqNRAuYdxAoT0nEeIh6GMvRNXkGT.rsKyY.MMwUitOnWcdS");
		
	}
	
	@Test
	void setPasswordTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		test.setPassword("test");
		Assert.assertEquals(test.getPassword(),"test");
		
	}
	
	@Test
	void getuserNameTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		Assert.assertEquals(test.getuserName(),"NaelAdmin");
	}
	
	@Test
	void setuserNameTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		test.setuserName("test");
		Assert.assertEquals(test.getuserName(),"test");
		
	}
	
	@Test
	void getphotoTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		Assert.assertEquals(test.getphoto(),null);
		
	}
	
	@Test
	void setphotoTest() {
		
		AppUser test = (AppUserDao.findById((long)1)).get();
		test.setphoto("Hola.jpg");
		Assert.assertEquals(test.getphoto(), "Hola.jpg");
	}
	
}
