/**
 * 
 */
package exemplos.java8.stream_collect02;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(
				new Person("Maria", 23), 
				new Person("Joaquina", 23),
				new Person("Manoela", 12), 
				new Person("Joao", 18)

		);

		Map<Integer, List<Person>> personsByAge = 
				persons.stream().
				collect(Collectors.groupingBy(p -> p.age));

		personsByAge.forEach((age, p) -> System.out.format("Idade: %s: %s\n", age, p));

		DoubleSummaryStatistics resumoIdade = 
				persons.stream().collect(
						Collectors.summarizingDouble(p -> p.age));

		System.out.println(resumoIdade);

		String frase = 
				persons.stream().
				filter(p -> 18 <= p.age ).
				map(p -> p.name)
				.collect(
						Collectors.joining(" e ", "Aqui no Brasil ", " podem beber."));

		System.out.println(frase);

		System.out.println("\nUso de collect com mapa");
		Map<Integer, String> map = 
				persons.stream()
				.collect(
					Collectors.toMap(
							p -> p.age, 
							p -> p.name, 
							(name1, name2) -> name1 + ";" + name2));

		System.out.println(map);

		System.out.println("\nCriação de um collector");
		Collector<Person, StringJoiner, String> 
		    personNameCollector = 
		    Collector.of(() -> new StringJoiner(" | "), // fornecedor
				(j, p) -> j.add(p.name.toUpperCase()), // acumulador
				(j1, j2) -> j1.merge(j2), // combinador
				StringJoiner::toString); // finalizador

		String names = persons.stream().collect(personNameCollector);

		System.out.println(names); // MAX | PETER | PAMELA | DAVID

	}

}
