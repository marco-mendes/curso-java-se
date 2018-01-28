/**
 * 
 */
package javaoito.exemplo04;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8 {

	public static void main(String[] args) {

		List<String> items = 
				Arrays.asList("Joao", "Joao", "Maria", "Joao", 
						      "Jose", "Maria", "Joaquina", "Jose",
				              "Carlos", "Joao", "Maria");

		Map<String, Long> result = items.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		logger.log(Level.INFO, "{0}", result);

		Map<String, Long> finalMap = new LinkedHashMap<>();

		// Ordena o mapa e adiciona para a variavel finalMap
		result.entrySet().stream()
		      .sorted(Map.Entry.<String, Long>comparingByKey().reversed())
			  .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

		logger.log(Level.INFO, "{0}", finalMap);

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
