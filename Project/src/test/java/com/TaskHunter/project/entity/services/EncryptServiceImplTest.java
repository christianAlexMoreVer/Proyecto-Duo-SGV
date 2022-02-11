package com.TaskHunter.project.entity.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.junit.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EncryptServiceImplTest {
	
	@Test
	void encryptPasswordTest() {
		
		String password = "hola";
		String passwordEncryptada = BCrypt.hashpw(password, BCrypt.gensalt());
		String passwordEncryptadaTest = BCrypt.hashpw(password, BCrypt.gensalt());
		boolean verificacionTest = BCrypt.checkpw(password, passwordEncryptada);
		boolean verificacion = BCrypt.checkpw(password, passwordEncryptadaTest);
		Assert.assertEquals(verificacion, verificacionTest);
		
	}
	
	@Test
	void verifyPasswordTest() {
		
		String originalPassword = "test";
		String passwordEncryptada = BCrypt.hashpw(originalPassword, BCrypt.gensalt());
		boolean verificacion = BCrypt.checkpw(originalPassword, passwordEncryptada);
		String passwordEncryptadaTest = BCrypt.hashpw(originalPassword, BCrypt.gensalt());
		boolean verificacionTest = BCrypt.checkpw(originalPassword, passwordEncryptada);
		Assert.assertEquals(verificacion, verificacionTest);
		
	}
	
}
