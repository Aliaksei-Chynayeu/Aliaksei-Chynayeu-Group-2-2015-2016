package com.epam.jmp.taskmanager.util;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.epam.jmp.taskmanager.model.Task;
import com.epam.jmp.taskmanager.model.TaskList;
import com.epam.jmp.taskmanager.model.TaskStore;

/**
 * Generator for TaskStore
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class TaskStoreGenearator {
	/**
	 * Empty constructor
	 */
	public TaskStoreGenearator() {
	}

	/**
	 * Gererates TaskStore
	 * 
	 * @return
	 */
	public static TaskStore generate() {

		Task task1 = new Task();
		task1.setId(Math.abs(new Random().nextLong()));
		task1.setName(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));
		task1.setDescription(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));

		Task task2 = new Task();
		task2.setId(Math.abs(new Random().nextLong()));
		task2.setName(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));
		task2.setDescription(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));

		Task task3 = new Task();
		task3.setId(Math.abs(new Random().nextLong()));
		task3.setName(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));
		task3.setDescription(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));

		Task task4 = new Task();
		task4.setId(Math.abs(new Random().nextLong()));
		task4.setName(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));
		task4.setDescription(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));

		Task task5 = new Task();
		task5.setId(Math.abs(new Random().nextLong()));
		task5.setName(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));
		task5.setDescription(RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN));

		TaskList taskList1 = new TaskList();
		taskList1.getTasks().add(task1);
		taskList1.getTasks().add(task2);
		taskList1.setId(Math.abs(new Random().nextLong()));

		TaskList taskList2 = new TaskList();
		taskList2.getTasks().add(task3);
		taskList2.getTasks().add(task4);
		taskList2.getTasks().add(task5);
		taskList2.setId(Math.abs(new Random().nextLong()));

		TaskStore ts = new TaskStore();
		ts.getTaskList().add(taskList1);
		ts.getTaskList().add(taskList2);
		ts.setId(Math.abs(new Random().nextLong()));
		
		return ts;
	}
}
