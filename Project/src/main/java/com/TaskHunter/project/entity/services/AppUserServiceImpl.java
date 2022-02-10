package com.TaskHunter.project.entity.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;

import com.TaskHunter.project.entity.dao.IAppUserDao;
import com.TaskHunter.project.entity.models.AppUser;


@Service
public class AppUserServiceImpl implements IAppUserService  {


	private final IAppUserDao AppUserDao;
	
	
	@Autowired
	public AppUserServiceImpl(IAppUserDao appUserDao) {
		
		AppUserDao = appUserDao;
	}

	@Override
	public List<AppUser> getAll() {

		return (List<AppUser>) AppUserDao.findAll();
	}

	@Override
	public void insert(AppUser AppUser) {

		AppUserDao.save(AppUser);

	}

	@Override
	public void delete(long id) {
		AppUserDao.deleteById(id);
	}

	@Override
	public void update(AppUser AppUser, long id) {
		if (AppUserDao.findById(id).isPresent()) {
			AppUser existingAppUser = AppUserDao.findById(id).get();

			try {
				if (AppUser.getemail().length() != 0) {
					existingAppUser.setemail(AppUser.getemail());
				}

			} catch (Exception e) {

			}

			try {
				if (AppUser.getPassword().length() != 0) {

					existingAppUser.setPassword(AppUser.getPassword());
				}
			} catch (Exception e) {

			}

			try {

				if (AppUser.getuserName().length() != 0) {
					
					
					if(!AppUserDao.findByuserName(AppUser.getuserName()).isPresent()) {
						existingAppUser.setuserName(AppUser.getuserName());
					}

					
				}
			} catch (Exception e) {

			}

			try {

				if (!AppUser.getphoto().equals(null)) {

					existingAppUser.setphoto(AppUser.getphoto());
				}
			} catch (Exception e) {

			}
			

			AppUserDao.save(existingAppUser);

		} else {
			System.out.print("No existe el elemento que se quiere actualizar");
		}

	}

	@Override
	public Optional<AppUser> findById(Long id) {

		return AppUserDao.findById(id);
	}

	@Override
	public AppUser loadUserByEmail(String email) throws UsernameNotFoundException {

		final Optional<AppUser> optionalUser = AppUserDao.findByEmail(email);

		if (optionalUser.isPresent()) {
			return (AppUser) optionalUser.get();
		}
		else {
			throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
		}
	}

	
	@Override
	public boolean findUserByUserName(String userName) {
		
		return AppUserDao.findByuserName(userName).isPresent();
	}

	@Override
	public boolean findUserByUserEmail(String email) {
		
		 return AppUserDao.findByEmail(email).isPresent();
	}

	@Override
	public List<AppUser> findUserByUsernameLike(String userName) {
		List<AppUser> Users = AppUserDao.findUserByuserNameContaining(userName);
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
		
		return Users;
	}

}
