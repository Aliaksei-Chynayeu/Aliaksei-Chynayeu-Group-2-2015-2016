package com.epam.jmp.sofsimulator.model;

import org.apache.log4j.Logger;


/**
 * Chicken model
 * @author Aliaksei_Hlazkou
 *
 */
public class Chicken{

	/** Logger */
	private static Logger LOG = Logger.getLogger(Chicken.class);
	/** Egg */
	private Egg egg;

	/**Constructor */
	public Chicken() {
		this.egg = new Egg();
		LOG.info("Chicken created.");
		
	}
}
