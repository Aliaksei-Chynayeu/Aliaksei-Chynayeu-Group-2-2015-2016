package com.epam.jmp.taskmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * List to store {@link Task} object
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskList implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = -4739159199995041582L;
	/** tasks list */
	@XmlElementWrapper(name="tasks")
    @XmlElement(name="task")
	private List<Task> tasks = new ArrayList<Task>();
	/** id */
	@XmlAttribute
	private long id;
	/**
	 * Getter for id filed
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for id field
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Getter for tasks field
	 * 
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * Steer for tasks field
	 * 
	 * @param tasks
	 *            the tasks to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "TaskList [tasks=" + tasks + ", id=" + id + "]";
	}

}
