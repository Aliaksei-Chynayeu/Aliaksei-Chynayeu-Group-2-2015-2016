package com.epam.jmp.classloader.util;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.epam.jmp.classloader.custom.CustomClassLoader;
import com.epam.jmp.classloader.model.Person;
import com.epam.jmp.classloader.util.ClassLoaderUtil;

/**
 * Test class for {@link com.epam.jmp.classloader.util.ClassLoaderUtil}
 * 
 * @author Aliaksei_Hlazkou
 */
public class ClassLoaderUtilTest extends Assert{
	
	/** Logger */
	private static final Logger LOG = Logger.getLogger(ClassLoaderUtil.class);
	/** Class name */
	public static final String CLASS_NAME = "com.epam.jmp.classloader.model.Person";
	/** Wrong class name */
	public static final String WRONG_CLASS_NAME = "com.epam.jmp.classloader.model.Foo";
	
	/**
	 * Successful test case for
	 * {@link com.epam.jmp.classloader.util.ClassLoaderUtil#loadClass(ClassLoader, String)}
	 */
	@Test
	public void loadClassSuccessfulTest() {
		Object instance = null;
		try {
			instance = ClassLoaderUtil.loadClass(new CustomClassLoader(), CLASS_NAME);
		} catch (Exception e) {
			LOG.debug((String.format("Class '%s' loading failed with %s", CLASS_NAME, e.getClass().getName())));
			LOG.debug(e);
			fail(e.getMessage());
		}
		assertTrue(instance instanceof Person);
	}
	
	/**
	 * Failure test case for
	 * {@link com.epam.jmp.classloader.util.ClassLoaderUtil#loadClass(ClassLoader, String)}
	 */
	@Test
	public void loadClassFailureTest() {
		Object instance = null;
		try {
			instance = ClassLoaderUtil.loadClass(new CustomClassLoader(), WRONG_CLASS_NAME);
		} catch (Exception e) {
			LOG.debug((String.format("Class '%s' loading failed with %s", WRONG_CLASS_NAME, e.getClass().getName())));
			LOG.debug(e.getStackTrace());
			fail(e.getMessage());
		}
		assertNull(instance);
	}
}
