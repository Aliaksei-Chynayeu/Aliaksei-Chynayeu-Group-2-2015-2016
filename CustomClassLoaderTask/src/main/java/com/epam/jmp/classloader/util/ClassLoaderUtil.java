package com.epam.jmp.classloader.util;

import org.apache.log4j.Logger;

import com.epam.jmp.classloader.run.App;

/**
 * Util for custom class loaders
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class ClassLoaderUtil {

	/** Logger */
	private static final Logger LOG = Logger.getLogger(App.class);

	/**
	 * Loads class with class loader
	 * 
	 * @param classLoader class loader object
	 * @param className name of loaded class
	 * @return instance of loaded class
	 */
	public static Object loadClass(ClassLoader classLoader, String className) {
		ClassLoader loader = classLoader;
		Class<?> clazz = null;
		Object instance = null;
		try {
			clazz = loader.loadClass(className);
			instance = clazz.newInstance();
		} catch (Exception e) {
			LOG.debug((String.format("Class '%s' loading failed with %s", className, e.getClass().getName())));
			LOG.debug(e);
		}
		if (instance != null) {
			LOG.info(String.format("Class '%s' was succesfully loaded", instance.getClass()));
		}
		return instance;
	}
}
