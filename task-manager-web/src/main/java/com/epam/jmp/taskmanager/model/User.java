package com.epam.jmp.taskmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User object
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class User  extends BeanBase implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 6020479177996826387L;
	
	/** User id */
	private long id;
	
	/** User role */
	private UserRole role;
	
    /** User first name */
	private String firstName;
	
    /** User last name */
	private String lastName;
   
    /** User Tasks */
	private List<Task> tasks = new ArrayList<Task>();

	
	public User() {
		super();
	}


	/**
	 * Getter for id filed
	 */
	public long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * Getter for role field
	 * 
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * Setter for role field
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
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
	 * Setter for tasks field
	 * 
	 * @param tasks
	 *            the tasks to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Setter for id filed
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (role != other.role)
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", tasks=" + tasks + "]";
	}

	

}
