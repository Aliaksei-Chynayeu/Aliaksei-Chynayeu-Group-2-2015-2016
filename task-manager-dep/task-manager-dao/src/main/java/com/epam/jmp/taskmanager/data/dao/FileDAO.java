package com.epam.jmp.taskmanager.data.dao;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.exception.TechnicalException;
import com.epam.jmp.taskmanager.model.TaskStore;
import com.epam.jmp.taskmanager.util.FileUtil;
import com.epam.jmp.taskmanager.util.JAXBUtil;

public class FileDAO extends AbstractDAO<TaskStore, File> {


	public TaskStore readFromFromFile() throws TechnicalDAOException {
		File file = this.getConnection();
		TaskStore taskStore = null;
		try {
			taskStore = JAXBUtil.unmarshall(TaskStore.class, file);
		} catch (TechnicalException e) {
			throw new TechnicalDAOException(e);
		} finally {
			this.releaseConnection(file);
		}
		return taskStore;
	}

	public void writeToFile(TaskStore taskStore) throws TechnicalDAOException {
		File file = this.getConnection();
		StringWriter sw = null;
		try {
			sw = JAXBUtil.marshall(taskStore, TaskStore.class);
			FileUtil.writeToFile(sw, file);
		} catch (Exception e) {
			throw new TechnicalDAOException(e);
		} finally {
			try {
				if (sw != null) {
					sw.close();
				}
			} catch (Exception e) {
				throw new TechnicalDAOException(e);
			}
			this.releaseConnection(file);
		}
	}

	public List<TaskStore> getList() throws TechnicalDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public TaskStore fetchById(long obj) throws TechnicalDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(TaskStore obj) throws TechnicalDAOException {
		// TODO Auto-generated method stub
		
	}

	public void remove(Long... ids) throws TechnicalDAOException {
		// TODO Auto-generated method stub
		
	}
}
