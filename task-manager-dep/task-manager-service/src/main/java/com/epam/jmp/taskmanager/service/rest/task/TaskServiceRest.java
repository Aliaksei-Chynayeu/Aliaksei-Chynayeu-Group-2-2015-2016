package com.epam.jmp.taskmanager.service.rest.task;

import javax.ws.rs.Path;
import org.apache.log4j.Logger;
import com.epam.jmp.taskmanager.service.rest.ServiceRest;
import com.epam.jmp.taskmanager.model.Task;

@Path("/task")
public class TaskServiceRest extends ServiceRest<Task>{

	public static final Logger LOG = Logger.getLogger(TaskServiceRest.class);
	
	public TaskServiceRest() {
	}
}