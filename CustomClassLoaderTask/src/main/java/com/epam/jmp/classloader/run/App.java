package com.epam.jmp.classloader.run;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.jmp.classloader.custom.CustomClassLoader;
import com.epam.jmp.classloader.util.ClassLoaderUtil;

/**
 * Application main class
 * 
 * @author Aliaksei_Hlazkou
 */
public class App {
	/** Logger */
	private static final Logger LOG = Logger.getLogger(App.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LOG.info("Application start.");
		LOG.info("Enter class name to load...");
		LOG.info("Example : 'com.epam.jmp.classloader.model.Person'");
		String className = scanner.nextLine();
		ClassLoaderUtil.loadClass(new CustomClassLoader(), className);
		LOG.info("Application finished.");
	}

}
