/**
 * 
 */
package exemplos.java8.streams_filter_map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        String nomeJoao = persons.stream()
                .filter(x -> "Joao".equals(x.getName()))
                .map(Person::getName)                        //Converte Stream to String
                .findAny()
                .orElse("");

        System.out.println("Nome: " + nomeJoao);

        System.out.println("\nLista de nomes da colecao com o map so Stream");

        List<String> collect = persons.stream()
                .map(Person::getName)  
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
        
    }


}
