/**
 * 
 */
package exemplos.java8.streams_filter_collect03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8FiltrosAdicionais {
    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("Joao", 30),
                new Person("Maria", 20),
                new Person("Jose", 40)
        );

        Person maria = persons.stream()                         // Converte para Stream
                .filter(x -> "Maria".equals(x.getName()))       // Apenas a Maria vai ser filtrada
                .findAny()                                      // Se encontrou 'findAny' retorna found
                .orElse(null);                                  // Se nao encontrou, retorna nada

        System.out.println(maria);

        Person carolina = persons.stream()
                .filter(x -> "Carolina".equals(x.getName()))
                .findAny()
                .orElse(null);

        System.out.println(carolina);
        
        persons = Arrays.asList(
                new Person("Manoela", 30),
                new Person("Bernardo", 20),
                new Person("Flavio", 40)
        );

        Person manoela = persons.stream()
                .filter((p) -> 
                  "Manoela".equals(p.getName()) && 30 == p.getAge())
                .findAny()
                .orElse(null);

        System.out.println(manoela);

        //Forma alternativa de escrita (mais verbosa)
        manoela = persons.stream()
                .filter(p -> {
                    if ("Manoela".equals(p.getName()) && 30 == p.getAge()) {
                        return true;
                    }
                    return false;
                }).findAny()
                .orElse(null);

        System.out.println(manoela);
        
    }


}
