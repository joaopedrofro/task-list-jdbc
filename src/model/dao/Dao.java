package model.dao;

import java.util.List;

public interface Dao<E> {

	E getById(Integer id);

	List<E> getAll();

	void add(E t);
	
	void update(E t);
	
	void delete(E t);
	
}
