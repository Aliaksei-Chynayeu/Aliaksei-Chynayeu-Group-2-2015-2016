package com.epam.jmp.taskmanager.data.dao.hibernate;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.epam.jmp.taskmanager.model.Task;

@Scope("singleton")
public class TaskHibernateDAO extends AbstractHibernateDAO<Task>{

	public static final Logger LOG = Logger.getLogger(TaskHibernateDAO.class);

	public TaskHibernateDAO() {
		super();
	}
	protected void init(){
		this.setEntityClassType(Task.class);
	}
	
}