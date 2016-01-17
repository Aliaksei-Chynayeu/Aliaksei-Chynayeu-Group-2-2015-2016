package com.epam.oomsimulator.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Util class for file
 * 
 * @author Aliaksei_Hlazkou
 */
public class FileUtil {

	/**
	 * Logger
	 */
	public static final Logger LOG = Logger.getLogger(FileUtil.class);

	/**
	 * Method to read file
	 * 
	 * @param readFileName
	 *            - name of file for reading
	 * @return info from file
	 */
	public static final StringBuilder readFile(String readFileName) {
		StringBuilder sb = new StringBuilder();
		Scanner s = null;
		FileReader fr = null;

		try {
			fr = new FileReader(readFileName);
			s = new Scanner(fr);

			while (s.hasNextLine()) {
				sb.append(s.nextLine() + "\n");
			}
			LOG.info("Read from file -> " + readFileName);
		} catch (FileNotFoundException e) {
			LOG.warn("Can NOT read from file -> " + readFileName + " " + e);
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return sb;
	}
}
