package com.epam.esm.dao;

import java.util.List;
import java.util.Optional;

import com.epam.esm.entity.Entity;

public interface BaseDao<T extends Entity> {
	
	T create(T t);
	
	List<T> findAll();
	
	Optional<T> findEntityById(long id);
	
	Optional<T> findEntityByName(String name);
	
	boolean delete(long id);

}
