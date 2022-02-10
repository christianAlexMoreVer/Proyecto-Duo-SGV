package com.TaskHunter.project.entity.services;

import com.TaskHunter.project.entity.models.AppUser;

public interface EncryptService {

	String encryptPassword(String password);
	
	boolean verifyPassword(String originalPassword, String hashPassword);
	

	
}
