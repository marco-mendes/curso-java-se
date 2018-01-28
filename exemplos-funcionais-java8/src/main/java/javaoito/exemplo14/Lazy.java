/**
 * 
 */
package javaoito.exemplo14;

import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class Lazy {
	public static void main(String[] args) {
		IntUnaryOperator sampleMap = num -> {
			System.out.println("number: " + num);
			return num;
		};
		Random random = new Random();
		IntStream randomStream = 
				random.ints().limit(5).
				map(sampleMap).
				sorted();
		System.out.println(randomStream); 

		// Veja que a impressao acima aparece antes das operacao do mapa
		// map é uma operacao Lazy (somente usada quando necessário)
		randomStream.forEach(System.out::println);
	}
}
