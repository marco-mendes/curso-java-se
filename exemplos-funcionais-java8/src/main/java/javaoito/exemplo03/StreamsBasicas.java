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
public class StreamsBasicas {

	public static void main(String[] args) {
		List<Person> persons = 
				Arrays.asList(
						new Person("Maria", 23), new Person("Joaquina", 23),
				        new Person("Manoela", 12), new Person("Joao", 18)
				        );

		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));

		personsByAge.forEach((age, p) -> logger.log(Level.INFO, "Idade: {0}: {1}", new Object[] { age, p }));

		DoubleSummaryStatistics resumoIdade = persons.stream().collect(Collectors.summarizingDouble(p -> p.age));

		logger.log(Level.INFO, "\n" + "{0}\n", resumoIdade);

		String frase = persons.stream().filter(p -> 18 <= p.age).map(p -> p.name)
				.collect(Collectors.joining(" e ", "Aqui no Brasil ", " podem beber."));

		logger.log(Level.INFO, "{0}", frase);


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
