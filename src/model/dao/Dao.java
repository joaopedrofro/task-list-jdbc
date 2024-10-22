package model.dao;

import java.util.List;
import java.util.Optional;

import model.dao.exceptions.DatabaseQueryException;

public interface Dao<E> {

	Optional<E> getById(Integer id) throws DatabaseQueryException;

	List<E> getAll() throws DatabaseQueryException;

	void add(E t) throws DatabaseQueryException;
	
	void update(E t) throws DatabaseQueryException;
	
	void delete(E t) throws DatabaseQueryException;
	
}
