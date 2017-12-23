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
public class ReduceParalelo {
	public static void main(String[] args) {
        	
		List<Person> persons = Arrays.asList(
			    new Person("Joao", 18),
			    new Person("Jose", 23),
			    new Person("Maria", 23),
			    new Person("Manoela", 12));

			persons
			    .parallelStream()
			    .reduce(0,
			        (sum, p) -> {
			            System.out.format("Acumulador: sum=%s; person=%s [%s]\n",
			                sum, p, Thread.currentThread().getName());
			            return sum += p.getAge();
			        },
			        (sum1, sum2) -> {
			            System.out.format("Combinador: sum1=%s; sum2=%s [%s]\n",
			                sum1, sum2, Thread.currentThread().getName());
			            return sum1 + sum2;
			        });

          
	}
}
