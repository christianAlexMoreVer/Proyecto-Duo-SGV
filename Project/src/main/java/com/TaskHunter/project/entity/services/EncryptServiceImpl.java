package com.TaskHunter.project.entity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.TaskHunter.project.entity.models.AppUser;

@Service
public class EncryptServiceImpl implements EncryptService {

	@Autowired
	private com.TaskHunter.project.entity.dao.IAppUserDao IAppUserDao;
	
	@Override
	public String encryptPassword(String password) {
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean verifyPassword(String originalPassword, String hashPassword) {
		
		return BCrypt.checkpw(originalPassword, hashPassword);
	}
	
}
