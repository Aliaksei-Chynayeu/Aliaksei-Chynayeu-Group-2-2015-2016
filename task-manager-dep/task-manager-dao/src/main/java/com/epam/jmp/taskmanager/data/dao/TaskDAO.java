package com.epam.jmp.taskmanager.data.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.model.Task;
import com.epam.jmp.taskmanager.util.TaskStoreGenearator;

public class TaskDAO extends AbstractDAO<Task, Connection>{

	public static final Logger LOG = Logger.getLogger(FileDAO.class);
	
	public TaskDAO() {
	}

	@Override
	public List<Task> getList() throws TechnicalDAOException {
		List<Task> tl = TaskStoreGenearator.generate().getTaskList().get(0).getTasks();
		LOG.info("Tasks were taken: " + tl);
		return tl;
	}

	@Override
	public Task fetchById(long obj) throws TechnicalDAOException {
		Task t = TaskStoreGenearator.generate().getTaskList().get(0).getTasks().get(0);
		LOG.info("Task was taken: " + t);
		return t;
	}

	@Override
	public Task save(Task task) throws TechnicalDAOException {
		LOG.info("Task was saved: " + task);
		return task;
	}

	@Override
	public void remove(Long... ids) throws TechnicalDAOException {
		LOG.info("Tasks with ids: " + ids + " - was deleted.");
	}

}
