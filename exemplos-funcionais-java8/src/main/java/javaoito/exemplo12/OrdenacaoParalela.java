/**
 * 
 */
package javaoito.exemplo12;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import javaoito.exemplo05.Person;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class OrdenacaoParalela {
	public static void main(String[] args) {
        	
		Arrays.asList("a1", "z2", "b1", "c2", "c1", "g2")
	    .parallelStream()
	    .sorted((s1, s2) -> {
	        System.out.format("Ordenacao (serial ou paralela): %s <> %s [%s]\n",
	            s1, s2, Thread.currentThread().getName());
	        return s1.compareTo(s2);
	    })
	    .filter(s -> {
	        System.out.format("Filtro paralelo: %s [%s]\n",
	            s, Thread.currentThread().getName());
	        return true;
	    })
	    .map(s -> {
	        System.out.format("Mapeamento paralelo: %s [%s]\n",
	            s, Thread.currentThread().getName());
	        return s.toUpperCase();
	    })
	    .forEachOrdered(s -> System.out.format("Processamento paralelo: %s [%s]\n",
	        s, Thread.currentThread().getName()));

		// Comportamento da ordenação
		// Javadoc - If the length of the specified array is less than 
		// the minimum granularity, then it is sorted using the 
		// appropriate Arrays.sort method.
		// Arrays.sort() ou Arrays.parallelSort() 

  		
		
	}
}
