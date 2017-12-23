/**
 * 
 */
package exemplos.java8.streams.reuso07;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.function.Supplier;
/**
 * @author Marco Mendes
 * @since 2017
 */

class Algo implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Algum processamento paralelo...");
		
	}
	
}
public class ComJava8 {
	public static void main(String[] args) {
	    String[] array = {"Joao", "Maria", "Jose", "Manoela", "Joaquina"};
	    Stream<String> stream = Arrays.stream(array);

	    // Loop em uma stream
	    System.out.println("\nPercorrendo a stream:");
	    stream.onClose(new Algo()).forEach(x -> System.out.println(x));

	    // Tentantiva de reuso da stream throws IllegalStateException
	    //long count = stream.filter(x -> "Maria".equals(x)).count();
	    //System.out.println(count);
	    
	    // Com a interface Supplier
	    Supplier<Stream<String>> streamSupplier = 
	    		() -> Stream.of(array);
	    
	    // Uso da stream com o supplier
	    System.out.println("\nPercorrendo uma stream com supplier:");
	    streamSupplier.get().forEach(x -> System.out.println(x));
	    
        //Obtenção de uma nova stream com o supplier
	    System.out.println("\nReuso de uma stream:");
	    long count = streamSupplier.get().filter(x -> "Maria".equals(x)).count();
        System.out.println(count);
	    
	}

}
