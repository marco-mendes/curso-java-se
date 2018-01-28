/**
 * 
 */
package javaoito.exemplo13;

import java.util.Optional;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class OptionalsAloMundo {
    public static void main(String[] args) {

    	// Optional é um tipo de Monad
    	// https://en.wikipedia.org/wiki/Monad_%28functional_programming%29#History
    	//  'Optional' vazio;
    	// Antes do Java 8 usariamos null como argumento
    	Optional<String> vazio = Optional.empty();
    	 
    	// Tipo Optional onde nao queremos que tenha um valor nulo 
    	// Se o parametro for nulo entao uma excecao NullPointException é atirada
    	// FailFast
    	Optional<String> cheio = Optional.of("Algo nao nulo");
    	 
    	// Um 'Optional' onde voce nao sabe se tera nulo ou nao.
    	String outraString = "Ola";
    	Optional<String> meioCheio = Optional.ofNullable(outraString);
    	
    	System.out.println("Optional Vazio: " + vazio.isPresent());
    	System.out.println("Optional Cheio: " + cheio.isPresent());
    	System.out.println("Optional MeioCheio: " + meioCheio.isPresent());
    	
    	System.out.println("Optional Cheio: " + cheio.get());
    	System.out.println("Optional MeioCheio: " + meioCheio.get());
    //  System.out.println("Optional Vazio: " + vazio.get()); NullPointerException
    	
   
    	
    }
    
    
}
