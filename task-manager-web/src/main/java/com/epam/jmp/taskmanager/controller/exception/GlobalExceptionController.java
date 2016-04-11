package com.epam.jmp.taskmanager.controller.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ex.printStackTrace();
		return new ModelAndView("global.exception");

	}
	
}