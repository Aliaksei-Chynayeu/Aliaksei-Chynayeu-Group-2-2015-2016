package com.epam.jmp.taskmanager.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

import com.epam.jmp.taskmanager.exception.TechnicalException;

/**
 * Util class for file
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class FileUtil {
	/**
	 * Constructor
	 */
	public FileUtil() {
	}

	/**
	 * Write to file
	 * 
	 * @param sw
	 * @param file
	 * @throws TechnicalException
	 */
	public static void writeToFile(StringWriter sw, File file)
			throws TechnicalException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(sw.toString());
		} catch (IOException e) {
			throw new TechnicalException(e);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				throw new TechnicalException(e);
			}
		}
	}

	/**
	 * Read form file
	 * 
	 * @param file
	 * @return
	 * @throws TechnicalException
	 */
	public static StringBuilder readFormFile(File file)
			throws TechnicalException {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new TechnicalException(e);
		}
		return sb;
	}
}
