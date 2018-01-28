package javaoito.exemplo06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2018
 */
public class ConversoesStreams {
	public static void main(String[] args) {

		Stream<String> nomes = Stream.of("Joao", "Maria", "Carlos");

		// Converte uma Stream para uma Lista
		List<String> result = nomes.collect(Collectors.toList());
		logger.info("\nConversao de stream para uma lista\nLista de nomes:");
		result.forEach(logger::info);

		// Converte um array para uma Stream
		// Arrays.stream
		String[] array = { "Jose", "Joaquim", "Maria", "Joana", "Joaquina" };
		Stream<String> stream1 = Arrays.stream(array);
		logger.info("\nConversao de uma lista para uma stream \nLista de nomes:");
		stream1.forEach(logger::info);

		// Converte uma Stream para uma Lista com aplicação de filtro
		Stream<Integer> number = Stream.of(1, 2, 3, 4, 5);
		List<Integer> result2 = number.filter(x -> x != 3).collect(Collectors.toList());
		logger.info("\nLista de numeros:");
		result2.forEach(x -> logger.info(String.valueOf(x)));

		// Conversao de tipos primitivos
		logger.info("\nConversao de tipos primitivos:\n");
		int[] intArray = { 1, 2, 3, 4, 5 };
		
		// 1. Arrays.stream -> IntStream
		IntStream intStream1 = Arrays.stream(intArray);
		intStream1.forEach(x -> logger.info(String.valueOf(x)));

		// 2. Stream.of -> Stream<int[]>
		Stream<int[]> temp = Stream.of(intArray);

		// Nao eh possivel imprimir Stream<int[]>, primeiro eh necessário converter para
		// IntStream
		IntStream intStream2 = temp.flatMapToInt(Arrays::stream);
		intStream2.forEach(x -> logger.info(String.valueOf(x)));

		// Converte uma Stream para um Mapa
		List<Hosting> list = new ArrayList<>();
		list.add(new Hosting(1, "g1.com.br", 80000));
		list.add(new Hosting(2, "youtube.com", 90000));
		list.add(new Hosting(3, "digitalocean.com", 120000));
		list.add(new Hosting(4, "amazon.com", 200000));
		list.add(new Hosting(5, "uol.com.br", 1));

		// key = id, value - WebSite
		Map<Integer, String> mapa1 = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getName));

		logger.log(Level.INFO, "{0} {1}", new Object[] { "\nConversao para mapa.\nResultado como mapa:", mapa1 });

		// Sintaxe alternativa
		Map<Integer, String> mapa2 = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getName));

		logger.log(Level.INFO, "{0} {1}", new Object[] { "\nResultado como mapa:", mapa2 });


		// key = name, value - websites
		Map<String, Long> mapa3 = list.stream().collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites));
		logger.log(Level.INFO, "{0} {1}", new Object[] { "\nResultado como mapa:", mapa3 });


		// Filtra valores de um mapa
		Map<Integer, String> hosts = new HashMap<>();
		hosts.put(1, "uol.com.br");
		hosts.put(2, "g1.com.br");
		hosts.put(3, "digitalocean.com");
		hosts.put(4, "aws.amazon.com");

		// Map -> Stream -> Filter -> String
		String resultadoFiltroMapa = 
				hosts.entrySet().stream().filter(map -> "aws.amazon.com".equals(map.getValue()))
				.map(Map.Entry::getValue).collect(Collectors.joining());

		logger.log(Level.INFO, "{0} {1}", new Object[] { "\nResultado:", resultadoFiltroMapa });

		// Conversao de um mapa para uma lista (basico e com o uso de streams)

		Map<Integer, String> map = new HashMap<>();
		map.put(10, "Lapis");
		map.put(20, "Caneta");
		map.put(30, "Post-It");
		map.put(40, "Regua");
		map.put(50, "Borracha");

		logger.info("\n1. Exporta o mapa para uma lista...");

		List<Integer> mapaPapelaria = new ArrayList<>(map.keySet());

		mapaPapelaria.forEach(valor -> logger.info(String.valueOf(valor)));

		logger.info("\n2. Exporta o mapa para uma lista...");

		List<String> novoMapaPaplearia = new ArrayList<>(map.values());

		novoMapaPaplearia.forEach(logger::info);

		// Quebra o mapa em duas listas (uso do metodo peek
		logger.info("\nQuebra do mapa em duas listas...");

		List<Integer> resultSortedKey = new ArrayList<>();
		List<String> resultValues = map.entrySet().stream()
				// Ordena o mapa por chave e armazena em resultSortedKey
				.sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
				.peek(e -> resultSortedKey.add(e.getKey())).map(Map.Entry::getValue)
				// Filtra o valor regua
				.filter(x -> !"Regua".equalsIgnoreCase(x)).collect(Collectors.toList());

		resultSortedKey.forEach(valor -> logger.info(String.valueOf(valor)));
		logger.info("\n");
		resultValues.forEach(valor -> logger.info(String.valueOf(valor)));

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
