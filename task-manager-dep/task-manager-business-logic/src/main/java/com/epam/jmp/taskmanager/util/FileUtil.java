package com.epam.jmp.taskmanager.util;

import java.io.File;
import java.io.FileWriter;
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
	private FileUtil() {
	}

	/**
	 * Write to file
	 * 
	 * @param sw
	 * @param file
	 * @throws TechnicalException
	 */
	public static void writeToFile(StringWriter sw, File file) throws TechnicalException {
		FileWriter fw = null;
		if (sw != null && sw != null) {
			try {
				fw = new FileWriter(file);
				fw.write(sw.toString());
			} catch (Exception e) {
				throw new TechnicalException(e);
			} finally {
				try {
					if (fw != null) {
						fw.close();
					}
				} catch (Exception e) {
					throw new TechnicalException(e);
				}
			}
		}
	}

	/**
	 * Read form file
	 * 
	 * @param file
	 * @return StringBuilder
	 * @throws TechnicalException
	 */
	public static StringBuilder readFormFile(File file) throws TechnicalException {
		StringBuilder sb = null;
		Scanner scanner = null;
		if (file != null) {
			try {
				sb = new StringBuilder();
				scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					sb.append(scanner.nextLine());
				}
			} catch (Exception e) {
				throw new TechnicalException(e);
			} finally {
				if (scanner != null) {
					scanner.close();
				}
			}
		}
		return sb;
	}
}
