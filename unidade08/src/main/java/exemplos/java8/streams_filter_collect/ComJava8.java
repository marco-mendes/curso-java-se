/**
 * 
 */
package exemplos.java8.streams_filter_collect;

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

        List<String> lines = Arrays.asList("Joao", "Maria", "Jose");
        List<String> result = lines.stream()                // Converte lista para stream
                .filter(line -> !"Maria".equals(line))      // Maria foi excluida
                .collect(Collectors.toList());              // Obtem a saida e converte para uma Lista

        result.forEach(System.out::println);              
        
        
    }


}
