package com.epam.jmp.taskmanager.data.dao.hibernate;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.model.Task;

public class TaskHibernateDAO extends HibernateDAO<Task>{

	public static final Logger LOG = Logger.getLogger(TaskHibernateDAO.class);

	public TaskHibernateDAO() {
		super();
	}
	protected void init(){
		this.setEntityClassType(Task.class);
	}
	
}