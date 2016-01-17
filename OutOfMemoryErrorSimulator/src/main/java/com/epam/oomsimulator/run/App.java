package com.epam.oomsimulator.run;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.oomsimulator.file.FileUtil;

/**
 * Application main class
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class App {
	
	/** Logger */
	private static Logger LOG = Logger.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		LOG.info("OutOfMempryError simulator's been started...");
		LOG.info("Enter file name to read. For example : 'src\\main\\resources\\testfile.txt'");
		Scanner sc = new Scanner(System.in);
		String fileName = sc.nextLine();
		String dataFromFile = FileUtil.readFile(fileName).toString();
		LOG.info("Data from file: \n" + dataFromFile);
		LOG.info("OutOfMempryError simulator's been started...");
	}

}