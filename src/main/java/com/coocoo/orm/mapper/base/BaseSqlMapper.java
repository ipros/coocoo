package com.coocoo.orm.mapper.base;

import java.util.List;

import org.springframework.dao.DataAccessException;


public interface BaseSqlMapper<T> extends SqlMapper {
	public List<T> getList(T entity) throws DataAccessException;
	
	public boolean add(T entity) throws DataAccessException;
}
