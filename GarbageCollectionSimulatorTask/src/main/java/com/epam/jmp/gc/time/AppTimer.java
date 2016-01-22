package com.epam.jmp.gc.time;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

/**
 * Reminder class
 * 
 * @author Aliaksei_Hlazkou
 */

public class AppTimer {
	/** Logger */
	private static final Logger LOG = Logger.getLogger(AppTimer.class);
	/** Timer */
	private Timer timer;
	/** Shows if application should run or not */
	private AtomicBoolean runApp;

	/**
	 * Constructor
	 * @param timeAmount time amount
	 * @param timeUnit time unit
	 */
	public AppTimer(long timeAmount, TimeUnit timeUnit) {
		timer = new Timer();
		timer.schedule(new StopAppTask(), TimeUnit.MILLISECONDS.convert(timeAmount, timeUnit));
		runApp = new AtomicBoolean(Boolean.TRUE);
		LOG.info(String.format("Application's been started for %d %s", timeAmount, timeUnit.toString().toLowerCase()));
	}

	/**
	 * Custom stop application class
	 */
	class StopAppTask extends TimerTask {
		
		/**
		 *  run
		 */
		public void run() {
			timer.cancel();
			runApp.set(Boolean.FALSE);
			LOG.info("It's time to fisnish applicaiton!");
		}
		
	}
	
	/**
	 * show if we still need run application
	 * @return if application should run
	 */
	public AtomicBoolean isRunApp () {
		return runApp;
	}
}