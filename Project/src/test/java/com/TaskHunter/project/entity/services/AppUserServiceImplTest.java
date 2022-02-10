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
	void insertTest() {}
	
	@Test
	void deleteTest() {}
	
	@Test
	void updateTest() {}
	
	@Test
	void findByIdTest() {}
	
	@Test
	void loadUserByEmailTest() {}
	
	@Test
    void findUserByUserNameTest() {}

    @Test
    void findUserByUserEmailTest() {
    	
        boolean resultTrue;
        resultTrue = this.AppUserDao.findByEmail("loloeladmin@gmail.com").isPresent();
        
        boolean resultFalse;
        resultFalse = this.AppUserDao.findByEmail("emailquenoexiste@gmail.com").isPresent();
        
        Assert.assertNotEquals( resultTrue, resultFalse );
        
    }
    
    @Test
    void findUserByUsernameLikeTest() {}
    
    
    

}