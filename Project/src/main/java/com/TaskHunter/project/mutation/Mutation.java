package com.TaskHunter.project.mutation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.TaskHunter.project.entity.dao.ICollectionDao;
import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.Collection;
import com.TaskHunter.project.entity.models.VideoGame;
import com.TaskHunter.project.entity.services.EncryptService;
import com.TaskHunter.project.entity.services.IAppUserService;
import com.TaskHunter.project.entity.services.ICollectionService;
import com.TaskHunter.project.entity.services.IVideoGameService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver{
	
	@Autowired
	IAppUserService AppUserService;

	@Autowired
	IVideoGameService VideoGameService;

	@Autowired
	EncryptService encryptService;

	@Autowired
	ICollectionService CollectionService;

	
	// -----------------------------AppUser------------------------------------------
	
	public String InsertAppUser(String email, String password , String userName, String imgFileName ,String img64)throws IOException {
		String hashPass = encryptService.encryptPassword(password);
		AppUser user = new AppUser(email, hashPass, userName, imgFileName);	
		String processedImg64 = img64.substring(img64.indexOf(",")+1);
		Decoder dec= Base64.getDecoder();
		OutputStream OS= null;
		byte[] fileBytes = dec.decode(processedImg64);
		OS = new FileOutputStream(new File("src/main/resources/static/img/AppUsers", imgFileName));
		OS.write(fileBytes);
		AppUserService.insert(user);
		return "Usuario creado :,)";
	}
	
	public String InsertAppUserWithOutImage( String email, String password , String userName )  {
		String hashPass = encryptService.encryptPassword(password);
		AppUser user = new AppUser(email, hashPass, userName);	
		AppUserService.insert(user);
		return "Usuario creado :,)";
		
	}
	
	public String InsertAdmin( String email, String password , String userName, int rol )  {
		String hashPass = encryptService.encryptPassword(password);
		AppUser user = new AppUser(email, hashPass, userName);	
		AppUserService.insert(user);
		return "Admin creado :,)";
		
	}
	
	
	

	public String UpdateAppUser(Long id, String email, String password , String userName, String imgFileName ,String img64 )throws IOException {
		
		AppUser user = new AppUser();
		
		user.setemail(email);
		user.setPassword(password);
		user.setuserName(userName);
		user.setphoto(img64);
		
		if(user.getPassword() != null) {
			String hashPass = encryptService.encryptPassword(user.getPassword());
			user.setPassword(hashPass);
		}
		String processedImg64 = img64.substring(img64.indexOf(",")+1);
		Decoder dec= Base64.getDecoder();
		OutputStream OS= null;
		byte[] fileBytes = dec.decode(processedImg64);
		OS = new FileOutputStream(new File("src/main/resources/static/img/AppUsers", imgFileName));
		OS.write(fileBytes);
		AppUserService.update(user, id);
		
		return "Se ha actualizado";
	}
	
public String UpdateImgUser(Long id, String imgFileName ,String img64 )throws IOException {
		AppUser user = new AppUser(imgFileName);
		String processedImg64 = img64.substring(img64.indexOf(",")+1);
		Decoder dec= Base64.getDecoder();
		OutputStream OS= null;
		byte[] fileBytes = dec.decode(processedImg64);
		OS = new FileOutputStream(new File("src/main/resources/static/img/AppUsers", imgFileName));
		OS.write(fileBytes);
		AppUserService.update(user, id);
		
		return "Se ha actualizado";
	}
	
	
	public String DeleteAppUser ( long id) {
		AppUserService.delete(id);
		
		return "Se ha borrado el usuario";
	}
	
// ----------------------------------------------------------------------------
	 
	
// ------------------------Videogame------------------------------------------------
		
	public String InsertVideoGame(String name, String imgFileName, String img64)throws IOException {
		VideoGame videogame = new VideoGame(name, imgFileName);
		String processedImg64 = img64.substring(img64.indexOf(",")+1);
		Decoder dec= Base64.getDecoder();
		OutputStream OS= null;
		byte[] fileBytes = dec.decode(processedImg64);
		OS = new FileOutputStream(new File("src/main/resources/static/img/Videogames", imgFileName));
		OS.write(fileBytes);
		VideoGameService.insert(videogame);
		
		return "Se ha creado el juego";
	}
	
	
	public String InsertVideoGameWithOutImage(String name) {
		
		VideoGame videogame = new VideoGame();
		
		videogame.setName(name);
		
		VideoGameService.insert(videogame);
		
		return "Se ha creado el juego";
	}
	
	
	public String UpdateVideoGameImage(int id, String imgFileName, String img64)throws IOException {
			
			VideoGame videogame = new VideoGame();
			
			videogame.setPhoto(imgFileName);
			
			String processedImg64 = img64.substring(img64.indexOf(",")+1);
			Decoder dec= Base64.getDecoder();
			OutputStream OS= null;
			byte[] fileBytes = dec.decode(processedImg64);
			OS = new FileOutputStream(new File("src/main/resources/static/img/Videogames", imgFileName));
			OS.write(fileBytes);
			VideoGameService.update(videogame, id);
			
			return "Se ha actualizado el juego";
		}
	
	public String UpdateVideoGame(int id, String name) {
		
		VideoGame videogame = new VideoGame();
		
		videogame.setName(name);
	
		VideoGameService.update(videogame, id);
		
		return "Se ha actualizado el juego";
	}
		
	
	public String DeleteVideoGame ( long id) {
		VideoGameService.delete(id);
		
		return "Se ha borrado el juego";
	}
	
// ----------------------------------------------------------------------------

// -------------------------Collection-----------------------------------------


public String InsertCollection ( long idAppUser, long idVideoGame, int State, int GameTime) {
	
	Collection collection = new Collection(idAppUser, idVideoGame, State, GameTime);
	
	CollectionService.insert(collection);
	
	return "Se ha añadido el videojuego a la colección";
}

public String UpdateVideoGameInCollection ( long idAppUser, long idVideoGame, int State, int GameTime) {
	
	Collection collection = new Collection(idAppUser, idVideoGame, State, GameTime);
	
	CollectionService.update(collection);
	
	return "Se ha actualizado el videojuego de la colección";
}

public String DeleteVideoGameInCollection ( long idAppUser, long idVideoGame) {
	
	Collection collection = new Collection();
	collection.setIdAppUser(idAppUser);
	collection.setIdVideoGame(idVideoGame);
	
	CollectionService.delete(collection);
	
	return "Se ha eliminado el videojuego de la colección";
}

public String CompleteVideoGame ( long idAppUser, long idVideoGame, int GameTime) {
	
	Collection collection = new Collection(idAppUser, idVideoGame, 1, GameTime);
	
	CollectionService.update(collection);
	
	return "Se ha actualizado el estado del videojuego de la colección";
}

public String NotCompleteVideoGame ( long idAppUser, long idVideoGame, int GameTime) {
	
	Collection collection = new Collection(idAppUser, idVideoGame, 0, GameTime);
	
	CollectionService.update(collection);
	
	return "Se ha actualizado el estado del videojuego de la colección";
}

//-----------------------------------------------------------------------------

}
