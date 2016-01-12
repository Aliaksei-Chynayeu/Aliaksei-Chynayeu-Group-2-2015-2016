package com.epam.jmp.classloader.custom;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.epam.jmp.classloader.model.Person;

/**
 * Unit test for
 * <class>com.epam.jmp.classloader.custom.CustomClassLoader</class>
 * 
 * @author Aliaksei_Hlazkou
 */
public class CustomClassLoaderTest extends Assert {

	/** Logger */
	private static final Logger LOG = Logger
			.getLogger(CustomClassLoaderTest.class);
	/** Class name */
	public static final String CLASS_NAME = "com.epam.jmp.classloader.model.Person";
	/** Wrong class name */
	public static final String WRONG_CLASS_NAME = "com.epam.jmp.classloader.model.Foo";

	/**
	 * Successful test case for
	 * <class>com.epam.jmp.classloader.custom.CustomClassLoader</class>
	 */
	@Test
	public void testCustomClassLoader() {
		LOG.info(String.format("Start test case for %s",
				CustomClassLoader.class));
		Class<?> clazz = null;
		Object instance = null;

		CustomClassLoader loader = new CustomClassLoader();
		assertNotNull(loader);

		try {
			clazz = loader.loadClass(CLASS_NAME);
		} catch (Exception e) {
			LOG.debug((String.format("Test case failed with %s", e.getClass()
					.getName())));
			LOG.debug(e.getStackTrace());
			fail(e.getMessage());
		}

		assertNotNull(clazz);

		try {
			instance = clazz.newInstance();
		} catch (Exception e) {
			LOG.debug((String.format("Test case failed with %s", e.getClass()
					.getName())));
			LOG.debug(e.getStackTrace());
			fail(e.getMessage());

		}

		assertNotNull(instance);
		assertTrue(instance instanceof Person);

		LOG.info(String.format("Test for %s succeed", CustomClassLoader.class));
	}

	/**
	 * Failure test case for empty parameter
	 * {@link com.epam.jmp.classloader.custom.CustomClassLoader#findClass(String)}
	 * . ClassNotFoundException is expected.
	 * @throws ClassNotFoundException 
	 */
	@Test(expected = ClassNotFoundException.class)
	public void testCustomClassLoaderEmptyClassName() throws ClassNotFoundException {
		new CustomClassLoader().findClass(StringUtils.EMPTY);
	}

	/**
	 * Failure test case for empty parameter
	 * {@link com.epam.jmp.classloader.custom.CustomClassLoader#findClass(String)}
	 * . ClassNotFoundException is expected.
	 * @throws ClassNotFoundException 
	 */
	@Test(expected = ClassNotFoundException.class)
	public void testCustomClassLoaderNullClassName() throws ClassNotFoundException {
			new CustomClassLoader().findClass(null);
	}

	/**
	 * Failure test case for non existing class name
	 * {@link com.epam.jmp.classloader.custom.CustomClassLoader#findClass(String)}
	 * . ClassNotFoundException is expected.
	 * @throws ClassNotFoundException 
	 */
	@Test(expected = ClassNotFoundException.class)
	public void testCustomClassLoaderClassNotFoundException() throws ClassNotFoundException {
			new CustomClassLoader().findClass(WRONG_CLASS_NAME);
	}
}
