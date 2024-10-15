package model.dao;

import java.util.List;

public interface Dao<T> {

	T getById(Integer id);

	List<T> getAll();

	void add(T t);
	
	void update(T t);
	
	void delete(T t);
	
}
