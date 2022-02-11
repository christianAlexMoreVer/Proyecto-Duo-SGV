package com.TaskHunter.project.query;

import com.TaskHunter.project.entity.dao.ICollectionDao;
import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.Collection;
import com.TaskHunter.project.entity.models.VideoGame;
import com.TaskHunter.project.entity.services.AppUserServiceImpl;
import com.TaskHunter.project.entity.services.EncryptService;
import com.TaskHunter.project.entity.services.IAppUserService;
import com.TaskHunter.project.entity.services.ICollectionService;
import com.TaskHunter.project.entity.services.IVideoGameService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	IAppUserService AppUserService;

	@Autowired
	IVideoGameService VideoGameService;

	@Autowired
	EncryptService encryptService;

	@Autowired
	ICollectionService CollectionService;

	@Autowired
	ICollectionDao CollectionDao;

	// ---------------------AppUser-----------------------------------------

	public List<AppUser> getUsers() {
		return AppUserService.getAll();
	}

	public Optional<AppUser> getUser(long id) {
		return AppUserService.findById(id);
	}

	public List<AppUser> getUserByUserNameLike(String userName) {

		return AppUserService.findUserByUsernameLike(userName);

	}

	public boolean getExistUserName(String userName) {

		return AppUserService.findUserByUserName(userName);

	}

	public boolean getExistEmail(String email) {

		return AppUserService.findUserByUserEmail(email);

	}

	public Optional<AppUser> getLogin(String email, String originalPassword) {

		try {
			AppUser loginUser = AppUserService.loadUserByEmail(email);

			if (encryptService.verifyPassword(originalPassword, loginUser.getPassword())) {
				return AppUserService.findById(loginUser.getIdAppUser());
			} else {

				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	// ----------------------VideoGame----------------------------------------------

	public List<VideoGame> getVideogames() {
		return VideoGameService.getAll();
	}

	public Optional<VideoGame> getVideogame(long id) {
		return VideoGameService.findById(id);
	}

	public boolean getExistVideogame(String name) {

		return VideoGameService.findByName(name);

	}

	public List<VideoGame> getVideogameByName(String name) {

		return VideoGameService.findVideoGameByNameContaining(name);

	}

	// --------------------------Collection------------------------------------------

	public List<Collection> CollectionByIdUser(long idAppUser) {
		return CollectionDao.findByIdAppUser(idAppUser);
	}

	public List<Collection> getCollections() {
		return CollectionService.getAll();
	}

	// --------------------------------------------------------------------------

}
