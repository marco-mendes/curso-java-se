/**
 * 
 */
package exemplos.java8.stream_conversoes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * @author Marco Mendes
 * @since 2017
 */
public class ComJava8 {
    public static void main(String[] args) {

        Stream<String> language = Stream.of("Joao", "Maria", "Carlos");

        //Converte uma Stream para uma Lista
        List<String> result = language.collect(Collectors.toList());
        System.out.println("\nConversao de stream para uma lista\nLista de nomes:"); 
        result.forEach(System.out::println);
        
        // Converte uma Lista para uma Stream
        //Arrays.stream
        String[] array = {"Jose", "Joaquim", "Maria", "Joana", "Joaquina"};
        Stream<String> stream1 = Arrays.stream(array);
        System.out.println("\nConversao de uma lista para uma stream \nLista de nomes:");        
        stream1.forEach(x -> System.out.println(x));
        
        //Converte uma Stream para uma Lista com aplicação de filtro            
        Stream<Integer> number = Stream.of(1, 2, 3, 4, 5);
        List<Integer> result2 = number.filter(x -> x!= 3).collect(Collectors.toList());
        System.out.println("\nLista de numeros:"); 
        result2.forEach(x -> System.out.println(x));
        
        // Conversao de tipos primitivos
        System.out.println("\nConversao de tipos primitivos:\n"); 

        int[] intArray = {1, 2, 3, 4, 5};

        // 1. Arrays.stream -> IntStream
        IntStream intStream1 = Arrays.stream(intArray);
        intStream1.forEach(x -> System.out.println(x));

        // 2. Stream.of -> Stream<int[]>
        Stream<int[]> temp = Stream.of(intArray);
        
        // Nao eh possivel imprimir Stream<int[]>, primeiro eh necessário converter para IntStream
        IntStream intStream2 = temp.flatMapToInt(x -> Arrays.stream(x));
        System.out.println();
        intStream2.forEach(x -> System.out.println(x));

        
        // Converte uma Stream para um Mapa
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "g1.com.br", 80000));
        list.add(new Hosting(2, "youtube.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "uol.com.br", 1));

        // key = id, value - WebSite
        Map<Integer, String> mapa1 = list.stream().collect(
                Collectors.toMap(Hosting::getId, Hosting::getName));

        System.out.println("\nConversao para mapa.\nResultado como mapa:" + mapa1);


        // Sintaxe alternativa  
        Map<Integer, String> mapa2 = list.stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x.getName()));

        System.out.println("\nResultado como mapa:" + mapa2); 

       
        
        
        // key = name, value - websites
        Map<String, Long> mapa3 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebsites));

        System.out.println("\nResultado como mapa: " + mapa3);
        
        
        // Filtra valores de um mapa
        Map<Integer, String> HOSTING = new HashMap<>();
        HOSTING.put(1, "uol.com.br");
        HOSTING.put(2, "g1.com.br");
        HOSTING.put(3, "digitalocean.com");
        HOSTING.put(4, "aws.amazon.com");
        
		//Map -> Stream -> Filter -> String
        String resultadoFiltroMapa = HOSTING.entrySet().stream()
                .filter(map -> "aws.amazon.com".equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());

        System.out.println("With Java 8 : " + resultadoFiltroMapa);

        
        // Conversao de um mapa para uma lista (basico e com o uso de streams)
        
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "Lapis");
        map.put(20, "Caneta");
        map.put(30, "Post-It");
        map.put(40, "Regua");
        map.put(50, "Borracha");

        System.out.println("\n1. Exporta o mapa para uma lista...");

        List<Integer> mapaPapelaria = new ArrayList(map.keySet());

        mapaPapelaria.forEach(System.out::println);

        System.out.println("\n2. Exporta o mapa para uma lista...");

        List<String> novoMapaPaplearia = new ArrayList(map.values());

        novoMapaPaplearia.forEach(System.out::println);
        
        // Quebra o mapa em duas listas (uso do metodo peek
        System.out.println("\nQuebra do mapa em duas listas...");

        List<Integer> resultSortedKey = new ArrayList<>();
        List<String> resultValues = map.entrySet().stream()
                //Ordena o mapa por chave e armazena em resultSortedKey
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .peek(e -> resultSortedKey.add(e.getKey()))
                .map(x -> x.getValue())
                // Filtra o valor regua 
                .filter(x -> !"Regua".equalsIgnoreCase(x))
                .collect(Collectors.toList());

        resultSortedKey.forEach(System.out::println);
        System.out.println("\n");
        resultValues.forEach(System.out::println);
        
 
    }
}
