/**
 * 
 */
package exemplos.java8.streams_filter_collect;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class RemocaoNull {
	public static void main(String[] args) {
		Stream<String> nomes = Stream.of("Maria", "Joao", "Jose", null, "Joaquina", null, "Manoela");

		List<String> result = nomes.collect(Collectors.toList());
        System.out.println("\nLista de nomes:");
		result.forEach(System.out::println);

		// Recria o stream 
		nomes = Stream.of("Maria", "Joao", "Jose", null, "Joaquina", null, "Manoela");
		
		// Filtra os nulos
		List<String> resultSemNulos = nomes.filter(x -> x != null).collect(Collectors.toList());
        System.out.println("\nLista de nomes:");
		resultSemNulos.forEach(System.out::println);

		
		// Recria o stream 
		nomes = Stream.of("Maria", "Joao", "Jose", null, "Joaquina", null, "Manoela");
		resultSemNulos = nomes.filter(Objects::nonNull).collect(Collectors.toList());       
		System.out.println("\nLista de nomes:");
		resultSemNulos.forEach(System.out::println);
		
	}

}
