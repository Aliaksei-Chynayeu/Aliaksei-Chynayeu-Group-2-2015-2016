package com.epam.jmp.taskmanager.data.dao.hibernate;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.epam.jmp.taskmanager.model.User;

@Scope("singleton")
public class UserHibernateDAO extends AbstractHibernateDAO<User>{

	public static final Logger LOG = Logger.getLogger(UserHibernateDAO.class);

	public UserHibernateDAO() {
		super();
	}
	protected void init(){
		this.setEntityClassType(User.class);
	}
}