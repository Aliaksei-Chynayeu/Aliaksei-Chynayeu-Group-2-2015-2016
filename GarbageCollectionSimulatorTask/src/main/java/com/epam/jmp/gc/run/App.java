package com.epam.jmp.gc.run;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.epam.jmp.gc.time.AppTimer;
import com.epam.jmp.gc.util.MagicNumbers;

/**
 * Application main class
 * 
 * @author Aliaksei_Hlazkou
 */
public class App {
	/** Logger */
	private static final Logger LOG = Logger.getLogger(App.class);

	public static void main(String[] args) {
		
		// Create application timer
		AppTimer appTimer = new AppTimer(MagicNumbers.MAGIC_FIVE, TimeUnit.MINUTES);
		
		//initialize map for filling
		Map<String, Integer> map = new HashMap();
		
		// iteration counter
		int iterationCounter = 0;
		
		//iteration loop
		while (appTimer.isRunApp().get()) {
			
			// random key generation
			String key = RandomStringUtils.randomAlphabetic(MagicNumbers.MAGIC_TEN);
			
			// random value generation
			int value = Math.abs(new Random().nextInt());
			
			map.put(key, value);
			
			LOG.info(String.format("Iteration: %d. Random: < %s , %d >", iterationCounter, key, value));
			
			iterationCounter++;
			
			// make pause
			try {
				TimeUnit.MILLISECONDS.sleep(MagicNumbers.MAGIC_FIVE_HUNDRED);
			} catch (InterruptedException e) {
				LOG.error(e);
			}
		}
		
		map = null;
		LOG.info("Map link was set as null.");
	}

}
