package com.epam.jmp.taskmanager.service.task.rest;

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
import com.epam.jmp.taskmanager.model.Task;
import com.epam.jmp.taskmanager.service.IService;

@Path("/task")
public class TaskServiceRest implements IService<Task>{

	public DAOImpl<Task> dao = null;
	
	public static final Logger LOG = Logger.getLogger(TaskServiceRest.class);
	
	public TaskServiceRest() {
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	public List<Task> read(@Context final HttpServletResponse response) throws ServiceRestException {
		List<Task> tasks = null;
		try {
			tasks = dao.getList();
			setResponceStatus(Response.Status.OK.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return tasks;

	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Task read(@PathParam("id") long id, @Context final HttpServletResponse response) throws ServiceRestException {
		Task task = null;
		try {
			task = dao.fetchById(id);
			setResponceStatus(Response.Status.OK.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return task;

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON})
	public Task create(Task task, @Context final HttpServletResponse response) throws ServiceRestException {
		Task newTask = null;
		try {
			task = dao.save(task);
			setResponceStatus(Response.Status.CREATED.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return newTask;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON})
	public Task update(Task task, @Context final HttpServletResponse response) throws ServiceRestException {
		Task newTask = null;
		try {
			task = dao.save(task);
			setResponceStatus(Response.Status.OK.getStatusCode(), response);
		} catch (TechnicalDAOException e) {
			setResponceStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response);
			throw new ServiceRestException(e);
		}
		return newTask;
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id, @Context final HttpServletResponse response) throws ServiceRestException {
		try {
			dao.remove(id);
		} catch (TechnicalDAOException e) {
			throw new ServiceRestException(e);
		}
	}

	public DAOImpl getDao() {
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
		    	LOG.warn("Responce status wasn't set.", ex);
		    }
	}
}