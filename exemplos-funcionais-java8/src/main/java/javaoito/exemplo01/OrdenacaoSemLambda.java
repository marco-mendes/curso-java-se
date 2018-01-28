
package javaoito.exemplo01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.CustomRecordFormatter;

public class OrdenacaoSemLambda {

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

		// Ordenacao por idade
		Collections.sort(listDevs, new Comparator<Developer>() {
			public int compare(Developer o1, Developer o2) {
				return o1.getAge() - o2.getAge();
			}
		});

		logger.info("Depois da ordenacao");
		for (Developer developer : listDevs) {
			logger.log(Level.INFO, "{0}", developer);
		}

	}

	private static List<Developer> getDevelopers() {

		List<Developer> result = new ArrayList<>();

		result.add(new Developer("Maria", new BigDecimal("70000"), 33));
		result.add(new Developer("Joao", new BigDecimal("80000"), 20));
		result.add(new Developer("Jose", new BigDecimal("100000"), 10));
		result.add(new Developer("Pedro", new BigDecimal("170000"), 55));

		return result;

	}

}

