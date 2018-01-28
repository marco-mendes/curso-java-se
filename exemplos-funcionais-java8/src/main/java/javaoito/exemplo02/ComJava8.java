package javaoito.exemplo02;

import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2018
 */
public class ComJava8 {
	public static void main(String[] args) {

		List<String> lines = Arrays.asList("Joao", "Maria", "Jose");
		List<String> result = lines.stream() // Converte lista para stream
				.filter(line -> !"Maria".equals(line)) // Maria foi excluida
				.collect(Collectors.toList()); // Obtem a saida e converte para uma Lista

		result.forEach(logger::info);

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