package services;

import java.util.Base64;
import java.util.Optional;

import model.dao.UserDao;
import model.dao.exceptions.DatabaseQueryException;
import model.entities.User;

public class AuthenticationService {

	private UserDao userDao;
	private User userAuthenticated;

	public AuthenticationService(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean authenticate(String username, String password) throws DatabaseQueryException {
		Optional<User> u = Optional.empty();

		try {
			u = userDao.getUserByName(username);
		} catch (DatabaseQueryException e) {
			throw e;
		}

		boolean authenticated = false;

		if (u.isPresent()) {
			byte[] salt = Base64.getDecoder().decode(u.get().getSalt());
			String hashPassword = PasswordHashingService.hashPassword(password, salt);
			if (u.get().getPassword().equals(hashPassword)) {
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
