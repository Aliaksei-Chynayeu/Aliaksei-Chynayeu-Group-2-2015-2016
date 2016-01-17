package com.epam.jmp.sofsimulator.run;

import org.apache.log4j.Logger;

import com.epam.jmp.sofsimulator.model.Chicken;
import com.epam.jmp.sofsimulator.model.Egg;

/**
 * Application main class
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class App {
	/** Logger */
	private static Logger LOG = Logger.getLogger(App.class);

	/**
	 * Application rum method. Creates {@link Chicken object} 
	 */
	public static void main(String[] args) {
		LOG.info("StackOverFlow simulator's started...");
		LOG.info("What Came First, the Chicken or the Egg? Let's check.");
		LOG.info("Lets create the Egg...");
		try{
			new Egg();
		} catch (StackOverflowError err) {
			LOG.error(err);
			LOG.info("Ooops..the Egg can not be created without the Chicken.");
		}
		LOG.info("Lets create the Chicken...");
		try{
		new Chicken();
		} catch (StackOverflowError err) {
			LOG.error(err);
			LOG.info("Ooops..the Chicken can not be created without the Egg.");
		}
		LOG.info("There is still no answer   :,(");
		LOG.info("StackOverFlow simulator's finished");
	}

}
