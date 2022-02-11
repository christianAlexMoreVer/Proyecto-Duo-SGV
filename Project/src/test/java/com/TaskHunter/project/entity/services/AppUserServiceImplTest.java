package com.TaskHunter.project.entity.services;

import com.TaskHunter.project.entity.dao.IAppUserDao;
import com.TaskHunter.project.entity.models.AppUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import org.junit.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserServiceImplTest {

	
	@Autowired
    IAppUserDao AppUserDao;
	
	@Test
	void getAllTest() {
		
		boolean comprobacion;
		List<AppUser> users = (List<AppUser>) AppUserDao.findAll();
		comprobacion = users.size() == 2;
		Assert.assertEquals(comprobacion, true);
		
	}
	
	@Test
	void insertTest() {
		
		boolean comprobacion;
		AppUser user = new AppUser("correo@gmail.com", "password", "test");
		AppUserDao.save(user);
		comprobacion = AppUserDao.findByEmail("correo@gmail.com").isPresent();
		Assert.assertEquals(comprobacion, true);
		AppUserDao.delete(user);
		
	}
	
	@Test
	void deleteTest() {
		
		boolean comprobacion;
		AppUser user = new AppUser("correo@gmail.com", "password", "test");
		AppUserDao.save(user);
		AppUserDao.delete(user);
		comprobacion = AppUserDao.findByEmail("correo@gmail.com").isPresent();
		Assert.assertEquals(comprobacion, false);
		
	}
	
	@Test
	void updateTest() {
		
		boolean comprobacion;
		AppUser user = new AppUser("correo@gmail.com", "password", "test");
		AppUserDao.save(user);
		AppUser userGet = AppUserDao.findByEmail("correo@gmail.com").get();
		userGet.setemail("Cambiado@gmail.com");
		userGet.setPassword("Cambiado");
		userGet.setuserName("Cambiado");
		userGet.setphoto(null);
		comprobacion = AppUserDao.findByEmail("Cambiado@gmail.com").isPresent();
		Assert.assertEquals(comprobacion, true);
		AppUserDao.delete(userGet);
		
	}
	
	@Test
	void findByIdTest() {
		
		boolean comprobacion;
		long id = (long) 1;
		comprobacion = this.AppUserDao.findById(id).isPresent();
		Assert.assertEquals(comprobacion, true);
		
	}
	
	@Test
	void loadUserByEmailTest() {
		
		boolean resultTrue;
        resultTrue = this.AppUserDao.findByEmail("loloeladmin@gmail.com").isPresent();
        
        boolean resultFalse;
        resultFalse = this.AppUserDao.findByEmail("emailquenoexiste@gmail.com").isPresent();
        
        Assert.assertNotEquals( resultTrue, resultFalse );
		
	}
	
	@Test
    void findUserByUserNameTest() {
		
		 	boolean resultTrue;
	        resultTrue = this.AppUserDao.findByuserName("NaelAdmin").isPresent();;
	        
	        boolean resultFalse;
	        resultFalse = this.AppUserDao.findByuserName("NaelAdminNoExisto").isPresent();;
	        
	        Assert.assertNotEquals( resultTrue, resultFalse );
		
	}

    @Test
    void findUserByUserEmailTest() {
    	
        boolean resultTrue;
        resultTrue = this.AppUserDao.findByEmail("loloeladmin@gmail.com").isPresent();
        
        boolean resultFalse;
        resultFalse = this.AppUserDao.findByEmail("emailquenoexiste@gmail.com").isPresent();
        
        Assert.assertNotEquals( resultTrue, resultFalse );
        
    }
    
    @Test
    void findUserByUsernameLikeTest() {
    	
    	List<AppUser> Users = AppUserDao.findUserByuserNameContaining("Nael");
		int position = 0;
		for (AppUser appUser : Users) {
			if(appUser.getRol() == 1) {
				Users.remove(position);
				break;
			}
			else {
				position++;
			}	
		}
		Assert.assertEquals(Users.size(), 1);
    }
    
    
    

}