package com.epam.jmp.taskmanager.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.epam.jmp.taskmanager.exception.ServiceRestException;

public interface IService<T> {

	public List<T> read(HttpServletResponse response)  throws ServiceRestException;

	public T read(long id, HttpServletResponse response)  throws ServiceRestException;

	public T create(T d, HttpServletResponse response)  throws ServiceRestException;

	public void delete(long id, HttpServletResponse response) throws ServiceRestException;

	public T update(T d, HttpServletResponse response)  throws ServiceRestException;
}
