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
 * Task store model for storing files in XML
 * 
 * @author Aliaksei_hlazkou
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskStore implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = -1470323951290290306L;
	/** tasks list */
	@XmlElementWrapper(name="lists")
    @XmlElement(name="list")
	List<TaskList> taskList = new ArrayList<TaskList>();
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
	 * Getter for taskList field
	 * 
	 * @return the taskList
	 */
	public List<TaskList> getTaskList() {
		return taskList;
	}

	/**
	 * Setter for taskList field
	 * 
	 * @param taskList
	 *            the taskList to set
	 */
	public void setTaskList(List<TaskList> taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "TaskStore [taskList=" + taskList + ", id=" + id + "]";
	}
	
}
