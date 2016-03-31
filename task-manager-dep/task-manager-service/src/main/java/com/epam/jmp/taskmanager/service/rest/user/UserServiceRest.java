package com.epam.jmp.taskmanager.service.rest.user;

import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.epam.jmp.taskmanager.model.User;
import com.epam.jmp.taskmanager.service.rest.AbstractServiceRest;

@Path("/user")
@Scope("prototype")
public class UserServiceRest  extends AbstractServiceRest<User> {

	public static final Logger LOG = Logger.getLogger(UserServiceRest.class);
	
	public UserServiceRest() {
	}

}
