package com.epam.jmp.taskmanager.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.epam.jmp.taskmanager.model.Task;



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
	private TaskStoreGenearator() {
	}

	/**
	 * Gererates TaskStore
	 * 
	 * @return
	 */
	public static List<Task> generate() {
		List<Task> list= new ArrayList<Task>();

		Task task1 = new Task();
		task1.setId(Math.abs(new Random().nextLong()));
		task1.setName(RandomStringUtils.randomAlphabetic(10));
		task1.setDescription(RandomStringUtils.randomAlphabetic(10));

		Task task2 = new Task();
		task2.setId(Math.abs(new Random().nextLong()));
		task2.setName(RandomStringUtils.randomAlphabetic(10));
		task2.setDescription(RandomStringUtils.randomAlphabetic(10));

		Task task3 = new Task();
		task3.setId(Math.abs(new Random().nextLong()));
		task3.setName(RandomStringUtils.randomAlphabetic(10));
		task3.setDescription(RandomStringUtils.randomAlphabetic(10));

		Task task4 = new Task();
		task4.setId(Math.abs(new Random().nextLong()));
		task4.setName(RandomStringUtils.randomAlphabetic(10));
		task4.setDescription(RandomStringUtils.randomAlphabetic(10));

		Task task5 = new Task();
		task5.setId(Math.abs(new Random().nextLong()));
		task5.setName(RandomStringUtils.randomAlphabetic(10));
		task5.setDescription(RandomStringUtils.randomAlphabetic(10));

		list.add(task1);
		list.add(task2);
		list.add(task3);
		list.add(task4);
		list.add(task5);
		
		return list;
	}
}
