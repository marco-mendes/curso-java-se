/**
 * 
 */
package javaoito.exemplo11;

import java.util.Arrays;
import java.util.List;

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
        
        // Reducao para 1 elemento da stream
        persons
        .stream()
        .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
        .ifPresent(System.out::println);    // Jose
        
        // Redução que cria 1 novo elemento do tipo da stream
        Person pessoaAgregada =
        	    persons
        	        .stream()
        	        .reduce(new Person("", 0), (p1, p2) -> {
        	            p1.setAge (p1.getAge() + p2.getAge());
           	        p1.setName (p1.getName() + p2.getName());
           	        return p1;
        	        });

        	System.out.format("Nome=%s; Idade=%s", 
        			          pessoaAgregada.getName(), 
        			          pessoaAgregada.getAge());
        	
        	// Redução que gera um acumulador
        	Integer ageSum = persons
        		    .stream()
        		    .reduce(0, (sum, p) -> p.getAge(), 
        		               (sum1, sum2) -> sum1 + sum2);

        	System.out.println("\nIdade acumulada " + ageSum);  // 90
        	
        	// Mesmo código, so que passo a passo
        	ageSum = persons
        		    .stream()
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
          
          // Observe que o combinador nunca é chamado quando o processamento serial
          // Vamos estudar o processamento paralelo e descobrir o porque...
         
          
        
	}
}
