package com.epam.jmp.taskmanager.controller.task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.jmp.taskmanager.model.Task;
import com.epam.jmp.taskmanager.util.TaskStoreGenearator;



@Controller
@RequestMapping("/task")
public class TaskController{

	@RequestMapping(value="/list", method = RequestMethod.GET)
    public ModelAndView getList() {
		System.out.println("----------GET LIST");
		ModelAndView mav = new ModelAndView("task.list", "taskList", TaskStoreGenearator.generate());
		return mav;
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ModelAndView getById(@PathVariable("id") Long id) {
		System.out.println("----------GET TASK BY ID " + id);
		ModelAndView mav = new ModelAndView("task.list", "taskList", TaskStoreGenearator.generate());
		return mav;
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String removeById(@PathVariable("id") Long id) {
		System.out.println("----------DELETE BY ID " + id);
	        return "redirect:/task/list";
	}
	@RequestMapping(value="/create", method = RequestMethod.GET)
    public ModelAndView create() {
		System.out.println("----------CREATE TASK ");
		return new ModelAndView("task.item.edit" , "task" , new Task());
    }
	@RequestMapping(value="/{id}/edit", method = RequestMethod.GET)
	public ModelAndView create(@PathVariable("id") Long id) {
		Task task = new Task();
		task.setId(id);
		return new ModelAndView("task.item.edit" , "task" , task);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String submit(@ModelAttribute("task") Task task, BindingResult result, ModelMap model) {
		model.addAttribute("task", task);
		System.out.println("-------- TASK WAS CREATED : " + task);
		return "task.item.view";
	}
}
