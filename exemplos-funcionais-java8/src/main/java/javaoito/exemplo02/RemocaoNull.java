/**
 * 
 */
package javaoito.exemplo02;

import java.util.List;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2018
 */
public class RemocaoNull {
	public static void main(String[] args) {
		Stream<String> nomes = Stream.of("Maria", "Joao", "Jose", null, "Joaquina", null, "Manoela");

		List<String> result = nomes.collect(Collectors.toList());
        logger.info("\nLista de nomes:");
		result.forEach(logger::info);

		// Recria o stream 
		nomes = Stream.of("Maria", "Joao", "Jose", null, "Joaquina", null, "Manoela");
		
		// Filtra os nulos
		List<String> resultSemNulos = nomes.filter(x -> x != null).collect(Collectors.toList());
		logger.info("\nLista de nomes:");
		resultSemNulos.forEach(logger::info);

		
		// Recria o stream 
		nomes = Stream.of("Maria", "Joao", "Jose", null, "Joaquina", null, "Manoela");
		resultSemNulos = nomes.filter(Objects::nonNull).collect(Collectors.toList());       
		logger.info("\nLista de nomes:");
		resultSemNulos.forEach(logger::info);
		
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
