package edu.school21.sockets.repositories;

import java.util.List;

public interface CrudRepository<T> {
	List<T> findAll();
	T findById(Long id);
	void save(T entity);
	void update(T entity);
	void delete(Long id);
}
