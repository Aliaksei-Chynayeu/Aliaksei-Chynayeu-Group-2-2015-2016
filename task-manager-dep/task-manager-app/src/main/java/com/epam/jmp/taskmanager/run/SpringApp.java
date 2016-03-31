package com.epam.jmp.taskmanager.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.jmp.taskmanager.exception.ServiceRestException;
import com.epam.jmp.taskmanager.service.IService;

public class SpringApp {

	public static void main(String[] args) {
		List<IService> serviceList = new ArrayList<IService>();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
		
		IService taskService = (IService) context.getBean("taskServiceRest");
		serviceList.add(taskService);
		
		
		IService userService = (IService) context.getBean("userServiceRest");
		serviceList.add(userService);
		
		try {
			for (IService service : serviceList) {
				service.create(null, null);
				service.update(null, null);
				service.delete(0L, null);
				service.read(0L, null);
			}
		} catch (ServiceRestException e) {
			e.printStackTrace();
		}
	}

}
