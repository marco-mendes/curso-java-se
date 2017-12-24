/**
 * 
 */
package exemplos.java8.streams.paralelismo12;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import exemplos.java8.streams_filter_map05.Person;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ProblemasStreamParalelasOrdenacao {
	public static void main(String[] args) {
        	
		// Ordenação Correta
		
		System.out.println("Ordenaco com forEach Ordered");
		Arrays.asList("5", "7", "8", "3", "-1", "2", "13")
	    .parallelStream()
	    .sorted((s1, s2) -> {
	        return s1.compareTo(s2);
	    })
	    .forEach(s -> System.out.print(" " + s));
		
		// Ordenação incorreta - Devido ao paralelismo e ao forEach

		System.out.println("\nOrdenacao paralela com forEach tradicional");
		Arrays.asList("5", "7", "8", "3", "-1", "2", "13")
	    .parallelStream()
	    .sorted((s1, s2) -> {
	        return s1.compareTo(s2);
	    })
	    .forEach(s -> System.out.print(" " + s));
	}
}
