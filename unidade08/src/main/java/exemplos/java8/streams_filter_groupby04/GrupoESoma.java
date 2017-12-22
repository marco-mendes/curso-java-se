/**
 * 
 */
package exemplos.java8.streams_filter_groupby04;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class GrupoESoma {

    public static void main(String[] args) {


        List<Item> items = Arrays.asList(
                new Item("Lapis", 10, new BigDecimal("9.99")),
                new Item("Borracha", 20, new BigDecimal("19.99")),
                new Item("Caneta", 10, new BigDecimal("29.99")),
                new Item("Post-Its", 10, new BigDecimal("29.99")),
                new Item("Lapis", 20, new BigDecimal("9.99")),
                new Item("Lapis", 10, new BigDecimal("9.99")),
                new Item("Borracha", 10, new BigDecimal("19.99")),
                new Item("Lapis", 20, new BigDecimal("9.99"))
        );

        // Agrupamento por lote de itens
        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println(counting);

        // Agrupamento por soma dos precos dos lotes
        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println(sum);
        
        // Agrupamento por preço
        Map<BigDecimal, List<Item>> groupByPriceMap =
			items.stream().collect(Collectors.groupingBy(Item::getPrice));

        System.out.println(groupByPriceMap);
        
        // Agrupamento por preço e mapeamento de de Lista para Conjunto (elimina repetições)
        Map<BigDecimal, Set<String>> result =
                items.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(Item::getName, Collectors.toSet())
                        )
                );

        System.out.println(result);

    }
	
}
