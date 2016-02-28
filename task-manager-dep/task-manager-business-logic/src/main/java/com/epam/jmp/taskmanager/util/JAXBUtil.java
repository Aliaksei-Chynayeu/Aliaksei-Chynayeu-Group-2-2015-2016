package com.epam.jmp.taskmanager.util;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.epam.jmp.taskmanager.exception.TechnicalException;

/**
 * Util class for JAXB usage
 * 
 * @author Aliaksei_Hlazkou
 */
public class JAXBUtil {

	/**
	 * Constructor
	 */
	private JAXBUtil() {
	}

	/**
	 * Unmarsall file with JAXB
	 * 
	 * @param typeParameter
	 *            object type parameter
	 * @param file
	 *            the file to unmarshall
	 * @return unmarshalled file
	 * @throws TechnicalException
	 *             in case of excpetion
	 */
	public static <T extends Object> T unmarshall(Class<T> typeParameter,
			File file) throws TechnicalException {
		T result = null;
		if (file != null) {
			try {
				JAXBContext jaxbContext = JAXBContext
						.newInstance(typeParameter);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				result = (T) jaxbUnmarshaller.unmarshal(file);
			} catch (Exception e) {
				throw new TechnicalException(e);
			}
		}
		return result;
	}

	/**
	 * Marshall the object with JAXB
	 * 
	 * @param obj
	 *            the object to be marshalled
	 * @param typeParameter
	 *            class instance parameter
	 * @param file
	 *            file to write
	 * @throws TechnicalException
	 *             in case of exception
	 */
	public static <T> StringWriter marshall(T obj, Class<T> typeParameter) throws TechnicalException {
		StringWriter sw = null;
		if (obj != null) {
			try {
				sw= new StringWriter();
				JAXBContext jaxbContext = JAXBContext
						.newInstance(typeParameter);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
						true);
				jaxbMarshaller.marshal(obj, sw);
			} catch (Exception e) {
				throw new TechnicalException(e);
			}
		}
		return sw;
	}
}
