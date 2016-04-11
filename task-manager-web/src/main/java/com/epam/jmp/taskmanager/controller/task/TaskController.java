package com.epam.jmp.taskmanager.controller.task;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.jmp.taskmanager.model.Task;

@Controller
@RequestMapping("/task")
public class TaskController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getList() throws Exception {
		List <Task> tasks =null;
		//call service
		ModelAndView mav = new ModelAndView("task.list", "taskList", tasks);
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(@PathVariable("id") Long id) throws Exception {
		Task task = null;
		//call service to get task
		ModelAndView mav = new ModelAndView("task.item.view", "task", task);
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ModelAndView removeById(@PathVariable("id") Long id) throws Exception {
		//call service to delete task
		return new ModelAndView("redirect:/task/list");
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() throws Exception {
		return new ModelAndView("task.item.edit", "task", new Task());
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView create(@PathVariable("id") Long id) throws Exception {
		Task task = null;
		//call service to get task for edition
		return new ModelAndView("task.item.edit", "task", task);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("task") Task task, ModelAndView mav) throws Exception {
		//call service to update task
		task = null; // task from service
		mav.addObject("task", task);
		mav.setViewName("task.item.view");
		return mav;
	}
}
