/**
 * 
 */
package exemplos.java8.streams.paralelismo12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ProblemasStreamsParalelasConcorrencia {
	public static void main(String[] args) {
		
		
		// O programa não funciona
		
		/*  List<Integer> hours = new ArrayList<>(
			        Arrays.asList(32, 40, 54, 23, 35, 48, 40, 40, 23, 
			            54, 45, 44, 45, 65, 34, 35, 42, 42, 50, 45, 
			            35, 45, 35, 31, 12, 56)); */
		  
		  CopyOnWriteArrayList<Integer> hours = 
		            new CopyOnWriteArrayList<>(
		            Arrays.asList(32, 40, 54, 23, 35, 48, 40, 40, 23, 
		                54, 45, 44, 45, 65, 34, 35, 42, 42, 50, 45, 
		                35, 45, 35, 31, 12, 56));		  
			    Stream<Integer> hoursStream;
			    hoursStream = hours.parallelStream();
			    int totalHours = hoursStream
			            .map(h -> {
			                int amount =h*30;
			                if(amount>40) {
			                    hours.add(h+10);
			                }
			                return amount;
			            })
			            .reduce(0, (r, s) -> r + s);
			    System.out.println(totalHours);

	}
}
