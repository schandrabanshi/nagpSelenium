/**
 * 
 */
package in.amazon.nagpselenium.reporterLogging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author sarojchandrabanshi
 *Logging based on different level of information or error.
 */
public class Logging  {

	public static final Logger log = LogManager.getLogger(Logging.class);
	/**
	 * Logging info with passed message.
	 * @param message
	 */
	public static void info(String message) {
		log.info(message);
	}
	/**
	 * Logging warning with passed message.
	 * @param message
	 */
	public static void warn(String message) {
		log.warn(message);
	}
	/**
	 * Logging error with passed message.
	 * @param message
	 */
	public static void error(String message) {
		log.error(message);
	}
	/**
	 * Logging fatal error message.
	 * @param message
	 */
	public static void fatal(String message) {
		log.fatal(message);
	}
	/**
	 * Logging debug message.
	 * @param message
	 */
	public static void debug(String message) {
		log.debug(message);
	}
	
}
