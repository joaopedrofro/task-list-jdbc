package services;

import java.util.Optional;

import model.dao.UserDao;
import model.entities.User;

public class AuthenticationService {

	private UserDao userDao;
	private User userAuthenticated;

	public AuthenticationService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public boolean authenticate(String username, String password) {
		Optional<User> u = userDao.getUsername(username);
		boolean authenticated = false;
		
		if (u.isPresent()) {
			if (u.get().getPassword().equals(password)) {
				authenticated = true;
				userAuthenticated = u.get();
			}
		}
		
		return authenticated;
	}
	
	public void logoutUserAuthenticated() {
		if (userAuthenticated != null) {
			userAuthenticated = null;
		}
	}
	
	public User getUserAuthenticated() {
		return userAuthenticated;
	}
	
}
