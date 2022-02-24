package com.TaskHunter.project.utils;

import org.springframework.stereotype.Service;

@Service
public class LocalStorage {
	
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}