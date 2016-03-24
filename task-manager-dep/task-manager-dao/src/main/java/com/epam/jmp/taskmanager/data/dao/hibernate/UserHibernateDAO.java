package com.epam.jmp.taskmanager.data.dao.hibernate;


import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.model.User;

public class UserHibernateDAO extends HibernateDAO<User>{

	public static final Logger LOG = Logger.getLogger(UserHibernateDAO.class);

	public UserHibernateDAO() {
		super();
	}
	protected void init(){
		this.setEntityClassType(User.class);
	}
}