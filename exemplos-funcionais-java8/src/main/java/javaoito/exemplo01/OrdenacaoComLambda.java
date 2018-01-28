package javaoito.exemplo01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.CustomRecordFormatter;

public class OrdenacaoComLambda {

	private static final Logger logger = Logger.getLogger("Exemplos");
	static {
		logger.setUseParentHandlers(false);
		CustomRecordFormatter formatter = new CustomRecordFormatter();
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.addHandler(consoleHandler);
	}

	public static void main(String[] args) {

		List<Developer> listDevs = getDevelopers();

		logger.info("Antes da ordenacao");
		for (Developer developer : listDevs) {
			logger.log(Level.INFO, "{0}", developer);
		}

		logger.info("\nDepois da ordenacao por idade\n");
		// Lambda (ordenacao por idade)
		listDevs.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());

		// Java 8 e lambda
		listDevs.forEach(developer -> logger.log(Level.INFO, "{0}", developer));

		logger.info("\nDepois da ordenacao por nome");
		listDevs.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		listDevs.forEach(developer -> logger.log(Level.INFO, "{0}", developer));

		logger.info("\nDepois da ordenacao por salario");
		listDevs.sort((o1, o2) -> o1.getSalary().compareTo(o2.getSalary()));
		listDevs.forEach(developer -> logger.log(Level.INFO, "{0}", developer));

	}

	private static List<Developer> getDevelopers() {

		List<Developer> result = new ArrayList<>();

		result.add(new Developer("Maria", new BigDecimal("70000"), 33));
		result.add(new Developer("Zenao", new BigDecimal("80000"), 20));
		result.add(new Developer("Jose", new BigDecimal("100000"), 10));
		result.add(new Developer("Abraao", new BigDecimal("170000"), 55));

		return result;

	}

}