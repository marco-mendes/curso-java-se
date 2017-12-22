
package exemplos.java8.lambda01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OrdenacaoSemLambda {

	public static void main(String[] args) {

		List<Developer> listDevs = getDevelopers();

		System.out.println("Before Sort");
		for (Developer developer : listDevs) {
			System.out.println(developer);
		}

		//sort by age
		Collections.sort(listDevs, new Comparator<Developer>() {
			public int compare(Developer o1, Developer o2) {
				return o1.getAge() - o2.getAge();
			}
		});

		System.out.println("After Sort");
		for (Developer developer : listDevs) {
			System.out.println(developer);
		}

	}

	private static List<Developer> getDevelopers() {

		List<Developer> result = new ArrayList<Developer>();

		result.add(new Developer("Maria", new BigDecimal("70000"), 33));
		result.add(new Developer("Joao", new BigDecimal("80000"), 20));
		result.add(new Developer("Jose", new BigDecimal("100000"), 10));
		result.add(new Developer("Pedro", new BigDecimal("170000"), 55));

		return result;

	}

}