/**
 * 
 */
package exemplos.java8.streams.ordem08;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8 {
	public static void main(String[] args) {
	
		System.out.println("\nMapeamento e casamento de valores:");		
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .map(s -> {
	        System.out.println("Mapeamento: " + s);
	        return s.toUpperCase();
	    })
	    .anyMatch(s -> {
	        System.out.println("Casamento: " + s);
	        return s.startsWith("A");
	    });

		System.out.println("\nMapeamento, filtro e processamento de valores:");		
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .map(s -> {
	        System.out.println("Mapeamento : " + s);
	        return s.toUpperCase();
	    })
	    .filter(s -> {
	        System.out.println("Filtro: " + s);
	        return s.startsWith("A");
	    }) 
	    .forEach(s -> System.out.println("Processamento " + s));

		System.out.println("\nFiltro, mapeamento e processamento de valores:");				
		System.out.println("\nCódigo bem mais eficiente:");				
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .filter(s -> {
	        System.out.println("Filtro: " + s);
	        return s.startsWith("a");
	    })
	    .map(s -> {
	        System.out.println("Mapeamento: " + s);
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("Processamento: " + s));

		System.out.println("\nOrdenação, filtro, mapeamento e processamento de valores:");				
		System.out.println("Operação de ordenação é stateful:");						
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .sorted((s1, s2) -> {
	        System.out.printf("Ordenação: %s; %s\n", s1, s2);
	        return s1.compareTo(s2);
	    })
	    .filter(s -> {
	        System.out.println("Filtro: " + s);
	        return s.startsWith("a");
	    })
	    .map(s -> {
	        System.out.println("Mapeamento: " + s);
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("Processamento: " + s));

		System.out.println("\nFiltro, ordenação, mapeamento e processamento de valores:");				
		System.out.println("Código mais eficiente:");						
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .filter(s -> {
	        System.out.println("Filtro: " + s);
	        return s.startsWith("a");
	    })
	    .sorted((s1, s2) -> {
	        System.out.printf("Ordenação: %s; %s\n", s1, s2);
	        return s1.compareTo(s2);
	    })
	    .map(s -> {
	        System.out.println("Mapeamento: " + s);
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("Processamento: " + s));
		
		
	}

}
