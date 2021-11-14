package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerPrinter<T> {

	private Logger logger;

	public LoggerPrinter(Class<T> loggerClass) {
		logger = LogManager.getLogger(loggerClass);
	}

	public void trace(String msg) {
		logger.trace(msg);
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void warn(Class<T> loggerClass, String msg) {
		logger.warn(msg);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void fatal(String msg) {
		logger.fatal(msg);
	}

	public Logger getLogger() {
		return logger;
	}

}
