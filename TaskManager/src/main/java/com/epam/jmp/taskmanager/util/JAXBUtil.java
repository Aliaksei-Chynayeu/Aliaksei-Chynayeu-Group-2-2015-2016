package com.epam.jmp.taskmanager.util;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.exception.TechnicalException;

/**
 * Util class for JAXB usage
 * 
 * @author Aliaksei_Hlazkou
 */
public class JAXBUtil {

	/**
	 * Logger
	 */
	public static final Logger LOG = Logger.getLogger(JAXBUtil.class);

	/**
	 * Constructor
	 */
	public JAXBUtil() {
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
		try {
			LOG.info("Trying to unmarshall data.");
			JAXBContext jaxbContext = JAXBContext.newInstance(typeParameter);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = (T) jaxbUnmarshaller.unmarshal(file);
			LOG.info("Data was unmarshalled");
		} catch (JAXBException e) {
			throw new TechnicalException(e);
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
	public static <T> StringWriter marshall(T obj, Class<T> typeParameter)
			throws TechnicalException {
		StringWriter sw = new StringWriter();
		try {
			LOG.info("Trying to marshall object...");
			JAXBContext jaxbContext = JAXBContext.newInstance(typeParameter);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(obj, sw);
			LOG.info("Object was marshalled");
		} catch (JAXBException e) {
			throw new TechnicalException(e);
		}
		return sw;
	}
}
