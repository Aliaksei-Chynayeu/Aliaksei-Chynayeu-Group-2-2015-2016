package com.epam.jmp.taskmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Task object
 * 
 * @author Aliaksei_Hlazkou
 * 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Task implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -371442436932271962L;
	/** Task name */
	@XmlElement
	private String name;
	/** Task description */
	@XmlElement
	private String description;
	/** Users that are assigned to the task */
	private List<User> assignedUsers = new ArrayList<User>();
	/** Task deadline */
	@XmlElement
	private GregorianCalendar deadline;
	/** Task start date */
	@XmlElement
	private GregorianCalendar startDate;
	/** Task finish date */
	@XmlElement
	private GregorianCalendar finishDate;
	/** Task id */
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
	 * Getter for name filed
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name filed
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for deadline filed
	 * 
	 * @return deadline
	 */
	public GregorianCalendar getDeadline() {
		return deadline;
	}

	/**
	 * Setter for deadline filed
	 * 
	 * @param deadline
	 *            deadline
	 */
	public void setDeadline(GregorianCalendar deadline) {
		this.deadline = deadline;
	}

	/**
	 * Getter for start date filed
	 * 
	 * @return start date
	 */
	public GregorianCalendar getStartDate() {
		return startDate;
	}

	/**
	 * Setter for start date filed
	 * 
	 * @param startDate
	 *            start date
	 */
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * Getter for finish date filed
	 * 
	 * @return finish date
	 */
	public GregorianCalendar getFinishDate() {
		return finishDate;
	}

	/**
	 * Setter for finish date filed
	 * 
	 * @param finishDate
	 *            finish date
	 */
	public void setFinishDate(GregorianCalendar finishDate) {
		this.finishDate = finishDate;
	}

	/**
	 * Getter for assigned users
	 * 
	 * @return the assignedUsers
	 */
	public List<User> getAssignedUsers() {
		return assignedUsers;
	}

	/**
	 * Setter for assigned users
	 * 
	 * @param assignedUsers
	 *            the assignedUsers to set
	 */
	public void setAssignedUsers(List<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}

	/**
	 * Getter for description filed
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for description filed
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignedUsers == null) ? 0 : assignedUsers.hashCode());
		result = prime * result
				+ ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((finishDate == null) ? 0 : finishDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Task other = (Task) obj;
		if (assignedUsers == null) {
			if (other.assignedUsers != null) {
				return false;
			}
		} else if (!assignedUsers.equals(other.assignedUsers)) {
			return false;
		}
		if (deadline == null) {
			if (other.deadline != null) {
				return false;
			}
		} else if (!deadline.equals(other.deadline)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (finishDate == null) {
			if (other.finishDate != null) {
				return false;
			}
		} else if (!finishDate.equals(other.finishDate)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description="
				+ description + ", assignedUsers=" + assignedUsers
				+ ", deadline=" + deadline + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + "]";
	}

}
