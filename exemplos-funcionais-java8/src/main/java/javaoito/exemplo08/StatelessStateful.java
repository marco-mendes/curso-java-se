/**
 * 
 */
package javaoito.exemplo08;

import java.util.function.Predicate;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class StatelessStateful {
	public static void main(String[] args) {

		logger.info("\nMapeamento e casamento de valores:");
		Stream.of("d2", "b1", "a2", "b3", "c").map(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Mapeamento:", s });
			return s.toUpperCase();
		}).anyMatch(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Casamento: ", s });
			return s.startsWith("A");
		});

		logger.info("\nMapeamento, filtro e processamento de valores:");
		Stream.of("d2", "A2", "A1", "b3", "c").map(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Mapeamento: ", s });
			return s.toUpperCase();
		}).filter(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Filtro:", s });
			return s.startsWith("A");
		}).forEach(s -> logger.log(Level.INFO, "{0} {1}", new Object[] { "Processamento: ", s }));

		logger.info("\nFiltro, mapeamento e processamento de valores:");
		logger.info("\nCódigo bem mais eficiente:");
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Filtro: ", s });
			return s.startsWith("a");
		}).map(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Mapeamento: ", s });
			return s.toUpperCase();
		}).forEach(s -> logger.log(Level.INFO, "{0} {1}", new Object[] { "Processamento: ", s }));

		logger.info("\nOrdenação, filtro, mapeamento e processamento de valores:");
		logger.info("Operação de ordenação é stateful:");
		Stream.of("d2", "a2", "b1", "b3", "c").sorted((s1, s2) -> {
			logger.log(Level.INFO, "{0} {1} {2}", new Object[] { "Ordenacao: ", s1, s2 });
			return s1.compareTo(s2);
		}).filter(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Filtro: ", s });
			return s.startsWith("a");
		}).map(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Mapeamento: ", s });
			return s.toUpperCase();
		}).forEach(s -> logger.log(Level.INFO, "{0} {1}", new Object[] { "Processamento: ", s }));

		logger.info("\nFiltro, ordenação, mapeamento e processamento de valores:");
		logger.info("Código mais eficiente:");
		Stream.of("d2", "a2", "a1", "b3", "c").filter(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Filtro: ", s });
			return s.startsWith("a");
		}).sorted((s1, s2) -> {
			logger.log(Level.INFO, "{0} {1} {2}", new Object[] { "Ordenação:", s1, s2 });
			return s1.compareTo(s2);
		}).map(s -> {
			logger.log(Level.INFO, "{0} {1}", new Object[] { "Mapeamento: ", s });
			return s.toUpperCase();
		}).forEach(s -> logger.log(Level.INFO, "{0} {1}", new Object[] { "Processamento: ", s }));

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
