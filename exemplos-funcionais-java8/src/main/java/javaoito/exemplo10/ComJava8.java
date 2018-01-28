/**
 * 
 */
package javaoito.exemplo10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Foo> foos = new ArrayList<>();

		// Cria foos
		IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));

		// Cria bars
		foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

		// Cria o flat map
		foos.stream()
	    .flatMap(f -> f.bars.stream())
	    .forEach(b -> System.out.println(b.name));
		
		// Versão compacta
		System.out.println("\nVersão compacta...");
		IntStream.range(1, 4)
	    .mapToObj(i -> new Foo("Foo" + i))
	    .peek(f -> IntStream.range(1, 4)
	        .mapToObj(i -> new Bar("Bar" + i + " <-"  + f.name))
	        .forEach(f.bars::add))
	    .flatMap(f -> f.bars.stream())
	    .forEach(b -> System.out.println(b.name));
		
	}

}
