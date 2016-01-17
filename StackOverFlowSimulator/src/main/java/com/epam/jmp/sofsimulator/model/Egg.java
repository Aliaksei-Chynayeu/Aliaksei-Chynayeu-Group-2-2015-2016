package com.epam.jmp.sofsimulator.model;

import org.apache.log4j.Logger;

/**
 * Egg model
 * @author Aliaksei_Hlazkou
 *
 */
public class Egg {

	/** Logger */
	private static Logger LOG = Logger.getLogger(Egg.class);
	/** Chicken */
	private Chicken chicken;
	
	/** Constructor */
	public Egg() {
		this.chicken = new Chicken();
		LOG.info("Egg created.");
	}
}
