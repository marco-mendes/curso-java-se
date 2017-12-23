/**
 * 
 */
package exemplos.java8.streams.totalizacoes09;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8 {
	public static void main(String[] args) {
		System.out.println("\nTotalização de valores de streams:");
		Arrays.stream(new int[] { 1, 2, 3 }).
		map(n -> 2 * n + 1).
		average().
		ifPresent(System.out::println); // 5.0

		System.out.println("\nTotalização de valores de streams:");		
		Stream.of("a1", "a2", "a3")
	    .map(s -> s.substring(1))
	    .mapToInt(Integer::parseInt)
	    .max()
	    .ifPresent(System.out::println);  // 3

		
	}

}
