package model.dao;

import java.util.Optional;

import model.dao.exceptions.DatabaseQueryException;
import model.entities.User;

public interface UserDao extends Dao<User> {
	Optional<User> getUserByName(String username) throws DatabaseQueryException;
}
