/**
 * 
 */
package exemplos.java8.streams_filter_groupby04;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8 {

    public static void main(String[] args) {

        List<String> items =
                Arrays.asList(
                		   "Joao", "Joao", "Maria", "Joao", "Jose","Maria", "Joaquina",
                        "Jose", "Carlos", "Joao", "Maria");

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
       

        System.out.println(result);
        
        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Ordena o mapa e adiciona para a variavel finalMap
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);


    }
	
}
