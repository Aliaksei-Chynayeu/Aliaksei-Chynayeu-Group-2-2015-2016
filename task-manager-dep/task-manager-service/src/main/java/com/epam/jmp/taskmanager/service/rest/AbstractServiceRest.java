package com.epam.jmp.taskmanager.service.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.data.dao.DAOImpl;
import com.epam.jmp.taskmanager.exception.ServiceRestException;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.service.IService;

public abstract class AbstractServiceRest<T> implements IService<T>{

	private DAOImpl<T> dao = null;
	
	public static final Logger LOG = Logger.getLogger(AbstractServiceRest.class);
	
	public void init () {
		
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	public List<T> read(@Context final HttpServletResponse response) throws ServiceRestException {
		List<T> objs = null;
		try {
			objs = (List<T>) getDao().getList();
			setResponceStatus(Response.Status.OK.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return objs;

	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public T read(@PathParam("id") long id, @Context final HttpServletResponse response) throws ServiceRestException {
		T obj = null;
		try {
			obj = (T) getDao().fetchById(id);
			setResponceStatus(Response.Status.OK.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return obj;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON})
	public T create(T obj, @Context final HttpServletResponse response) throws ServiceRestException {
		T newObj = null;
		try {
			newObj = (T) getDao().save(obj);
			setResponceStatus(Response.Status.CREATED.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return newObj;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON})
	public T update(T obj, @Context final HttpServletResponse response) throws ServiceRestException {
		T newT;
		try {
			newT = (T) getDao().save(obj);
			setResponceStatus(Response.Status.OK.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return newT;
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id, @Context final HttpServletResponse response) throws ServiceRestException {
		try {
			getDao().remove(id);
		} catch (TechnicalDAOException e) {
			throw new ServiceRestException(e);
		}
	}

	public DAOImpl getDao() throws ServiceRestException {
		if(dao == null) {
			throw new ServiceRestException("Service can not be initialized. DAO is null.");
		}
		return dao;
	}

	public void setDao(DAOImpl dao) {
		this.dao = dao;
	}

	private void setResponceStatus(int statusCode, HttpServletResponse response) {
		response.setStatus(statusCode);
		  try {
		        response.flushBuffer();
		    } catch (Exception ex){
		    	getLogger().warn("Responce status wasn't set.", ex);
		    }
	}
	@Override
	public Logger getLogger() {
		return LOG;
	}
}