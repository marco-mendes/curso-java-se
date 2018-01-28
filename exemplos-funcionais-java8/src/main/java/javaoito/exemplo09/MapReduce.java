package javaoito.exemplo09;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Stream;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class MapReduce {
	public static void main(String[] args) {
		logger.info("\nTotalização de valores de streams:");
		Arrays
		  .stream(new int[] { 1, 2, 3 })
		  .map(n -> 2 * n + 1)
		  .average()
		  .ifPresent(valor -> logger.log(Level.INFO, "{0}", valor)); // 5.0

		logger.info("\nTotalização de valores de streams:");
		Stream
		   .of("a1", "a2", "a3")
		   .map(s -> s.substring(1))
		   .mapToInt(Integer::parseInt)
		   .max()
		   .ifPresent(valor -> logger.log(Level.INFO, "{0}", valor)); // 3

	}

	private static final Logger logger = Logger.getLogger("Exemplos");
	static {
		logger.setUseParentHandlers(false);
		CustomRecordFormatter formatter = new CustomRecordFormatter();
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.addHandler(consoleHandler);
	}

}
