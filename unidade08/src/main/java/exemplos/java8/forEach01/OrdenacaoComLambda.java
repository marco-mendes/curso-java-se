package exemplos.java8.forEach01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrdenacaoComLambda {

	public static void main(String[] args) {

		List<Developer> listDevs = getDevelopers();

		System.out.println("\nAntes da ordenacao");		
		// Sem forEach (Java 7)
		for (Developer developer : listDevs) {
			System.out.println(developer);
		}
		
		// Exemplo do uso do ForEach (Java 8)
		System.out.println("\nAntes da ordenacao");		
		listDevs.forEach(item -> System.out.println(item));
		
		// Exemplo do uso do ForEach com recurso chamado method reference
		System.out.println("\nAntes da ordenacao");		
		listDevs.forEach(System.out::println);

	

		System.out.println("\nDepois da ordenacao por idade\n");
		// Lambda (ordenacao por idade)
		listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());
		// Java 8 e lambda
		listDevs.forEach((developer)->System.out.println(developer));

		System.out.println("\nDepois da ordenacao por nome\n");
		listDevs.sort((o1, o2)->o1.getName().compareTo(o2.getName()));
		listDevs.forEach((developer)->System.out.println(developer));

		System.out.println("\nDepois da ordenacao por salario\n");
		listDevs.sort((o1, o2)->o1.getSalary().compareTo(o2.getSalary()));
		listDevs.forEach((developer)->System.out.println(developer));
		
	}

	private static List<Developer> getDevelopers() {

		List<Developer> result = new ArrayList<Developer>();

		result.add(new Developer("Maria", new BigDecimal("70000"), 33));
		result.add(new Developer("Zenao", new BigDecimal("80000"), 20));
		result.add(new Developer("Jose", new BigDecimal("100000"), 10));
		result.add(new Developer("Abraao", new BigDecimal("170000"), 55));

		return result;

	}

}