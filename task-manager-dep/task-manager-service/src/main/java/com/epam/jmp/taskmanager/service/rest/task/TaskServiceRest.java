package com.epam.jmp.taskmanager.service.rest.task;

import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.epam.jmp.taskmanager.service.rest.AbstractServiceRest;
import com.epam.jmp.taskmanager.model.Task;

@Path("/task")
@Scope("prototype")
public class TaskServiceRest extends AbstractServiceRest<Task>{

	public static final Logger LOG = Logger.getLogger(TaskServiceRest.class);
	
	public TaskServiceRest() {
	}
}