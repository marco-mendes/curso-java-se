package javaoito.exemplo03;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2018
 */
public class StreamsComMapeamento {

	public static void main(String[] args) {
		List<Person> persons = 
				Arrays.asList(
						new Person("Maria", 23), new Person("Joaquina", 23),
				        new Person("Manoela", 12), new Person("Joao", 18)
				        );

		logger.info("\nUso de collect com mapa");
		Map<Integer, String> map = persons.stream()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + ";" + name2));

		logger.log(Level.INFO, "{0}", map);

		logger.info("\nCriação de um collector");
		Collector<Person, StringJoiner, String> personNameCollector = 
				Collector.of(() -> new StringJoiner(" | "), // fornecedor
				(j, p) -> j.add(p.name.toUpperCase()), // acumulador
				(j1, j2) -> j1.merge(j2), // combinador
				StringJoiner::toString); // finalizador

		String names = persons.stream().collect(personNameCollector);

		logger.log(Level.INFO, "{0}", names);

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
