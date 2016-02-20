package com.epam.jmp.taskmanager.data.dao;

import java.util.List;

import com.epam.jmp.taskmanager.exception.TechnicalDAOException;

/**
 * Interface for the DAO level
 * 
 * @author Aliaksei_Hlazkou
 * 
 * @param <T>
 *            - object to work with
 */
public interface DAOImpl<T> {

	public List<T> getList() throws TechnicalDAOException;

	public T fetchById(long obj) throws TechnicalDAOException;

	public void save(T obj) throws TechnicalDAOException;

	public void remove(Long...ids) throws TechnicalDAOException;

}
