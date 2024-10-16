package model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E> {

	Optional<E> getById(Integer id);

	List<E> getAll();

	void add(E t);
	
	void update(E t);
	
	void delete(E t);
	
}
