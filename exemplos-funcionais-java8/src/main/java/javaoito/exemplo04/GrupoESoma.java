package javaoito.exemplo04;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2018
 */
public class GrupoESoma {

	public static void main(String[] args) {

		List<Item> items = Arrays.asList(new Item("Lapis", 10, new BigDecimal("9.99")),
				new Item("Borracha", 20, new BigDecimal("19.99")), new Item("Caneta", 10, new BigDecimal("29.99")),
				new Item("Post-Its", 10, new BigDecimal("29.99")), new Item("Lapis", 20, new BigDecimal("9.99")),
				new Item("Lapis", 10, new BigDecimal("9.99")), new Item("Borracha", 10, new BigDecimal("19.99")),
				new Item("Lapis", 20, new BigDecimal("9.99")));

		// Agrupamento por lote de itens
		Map<String, Long> counting = items.stream()
				.collect(Collectors.groupingBy(Item::getName, Collectors.counting()));

		logger.log(Level.INFO, "{0}", counting);

		// Agrupamento por soma dos precos dos lotes
		Map<String, Integer> sum = items.stream()
				.collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

		logger.log(Level.INFO, "{0}", sum);

		// Agrupamento por preço
		Map<BigDecimal, List<Item>> groupByPriceMap = 
				items.stream().collect(Collectors.groupingBy(Item::getPrice));

		logger.log(Level.INFO, "{0}", groupByPriceMap);


		// Agrupamento por preço e mapeamento de de Lista para Conjunto (elimina
		// repetições)
		Map<BigDecimal, Set<String>> result = items.stream()
				.collect(Collectors.groupingBy(Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())));

		logger.log(Level.INFO, "{0}", result);

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
