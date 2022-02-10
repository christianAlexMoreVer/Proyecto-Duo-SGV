package com.TaskHunter.project.entity.services;

import com.TaskHunter.project.entity.dao.IAppUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserServiceImplTest {

	
	@Autowired
    IAppUserDao AppUserDao;

    @Test
    void findUserByUserEmailTest() {
    	
        boolean resultTrue;
        resultTrue = this.AppUserDao.findByEmail("loloeladmin@gmail.com").isPresent();
        
        boolean resultFalse;
        resultFalse = this.AppUserDao.findByEmail("emailquenoexiste@gmail.com").isPresent();
        
        Assert.assertNotEquals( resultTrue, resultFalse );
    }

}