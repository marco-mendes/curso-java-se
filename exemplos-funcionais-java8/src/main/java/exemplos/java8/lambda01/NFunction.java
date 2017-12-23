/**
 * 
 */
package exemplos.java8.lambda01;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class NFunction {
	public static void main(String[] args) {
		// Uso da interface Function
		Function<Integer, Integer> dobra = 
				(a) -> a.intValue() *2;
				
		System.out.println(dobra.apply(4));

		// Uso da interface BiFunction
		BiFunction<Integer, Integer, Integer>
		   soma = (a, b) -> a +b;
		System.out.println(soma.apply(4,2));

	}
}
