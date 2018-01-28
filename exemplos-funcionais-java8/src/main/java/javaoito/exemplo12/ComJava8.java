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
public class ComJava8 {
	public static void main(String[] args) {
        	
        List<Person> persons = Arrays.asList(
                new Person("Joao", 30),
                new Person("Maria", 20),
                new Person("Jose", 40)
        );

        	// Processamento paralelo, com modelo Fork and Join
        	int ageSum = persons
        		    .parallelStream()
        		    .reduce(0,
        		        (sum, p) -> {
        		            System.out.format("Acumulador: sum=%s; person=%s\n", sum, p);
        		            return sum += p.getAge();
        		        },
        		        (sum1, sum2) -> {
        		            System.out.format("Combinador: sum1=%s; sum2=%s\n", sum1, sum2);
        		            return sum1 + sum2;
        		        });
          System.out.println("\nIdade acumulada " + ageSum);  // 90
          
          // Numero de nucleos disponiveis para processamento paralelo
          ForkJoinPool commonPool = ForkJoinPool.commonPool();
          System.out.println("\nNucleos de processamento:" + commonPool.getParallelism());    
          
          // Controle do paralelimo
          // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        
          // Filtro, mapeamento e processamento paralelo
          System.out.println("\nProcessamento paralelo:" + commonPool.getParallelism());    
          Arrays.asList("a1", "a2", "b1", "c2", "c1")
          .parallelStream()
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
          .forEach(s -> System.out.format("Processamento paralelo: %s [%s]\n",
              s, Thread.currentThread().getName()));
          
	}
}
