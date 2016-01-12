package com.epam.jmp.classloader.custom;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Custom class loader
 * 
 * @author Aliaksei_Hlazkou
 */
public class CustomClassLoader extends ClassLoader {

	/** Logger */
	private static final Logger LOG = Logger.getLogger(CustomClassLoader.class);

	/** Cashed classes */
	private static HashMap<String, Class<?>> classes = new HashMap();

	/**
	 * Constructor
	 * 
	 * @param classLoader
	 *            <code>java.lang.ClassLoader</code> object
	 */
	public CustomClassLoader(ClassLoader classLoader) {
		super(CustomClassLoader.class.getClassLoader());
	}

	/** Constructor */
	public CustomClassLoader() {
		super(CustomClassLoader.class.getClassLoader());
	}

	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException,
			IllegalArgumentException {
		return findClass(className);
	}

	@Override
	public Class<?> findClass(String className) throws ClassNotFoundException {
		if (StringUtils.isEmpty(className)) {
			throw new ClassNotFoundException("Class name can not be null or empty");
		}
		Class<?> result = null;
		result = (Class<?>) classes.get(className);
		if (result == null) {
			result = findSystemClass(className);
	}
		if (result != null) {
			classes.put(className, result);
		}
		return result;
	}

}