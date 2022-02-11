package com.TaskHunter.project.entity.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImpl implements EncryptService {

	@Override
	public String encryptPassword(String password) {
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean verifyPassword(String originalPassword, String hashPassword) {
		
		return BCrypt.checkpw(originalPassword, hashPassword);
	}
	
}
