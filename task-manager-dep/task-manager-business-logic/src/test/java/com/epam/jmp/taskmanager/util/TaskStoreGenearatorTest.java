package com.epam.jmp.taskmanager.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.epam.jmp.taskmanager.model.TaskStore;

/**
 * Test class for {@link TaskStoreGenearator}
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
@RunWith(JUnit4.class)
public class TaskStoreGenearatorTest {
	
	/**
	 * Test method for {@link TaskStoreGenearator#generate()}
	 */
	@Test
	public void testGenerate() {
		Object generatedObj = TaskStoreGenearator.generate();
		Assert.assertNotNull(generatedObj);
		Assert.assertTrue(generatedObj instanceof TaskStore);
	}
}
