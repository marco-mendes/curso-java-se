/**
 * 
 */
package exemplos.java8.streams_filter_collect03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class AntesJava8 {
    public static void main(String[] args) {

        List<String> lines = Arrays.asList("Joao", "Maria", "Jose");
        List<String> result = getFilterOutput(lines, "Maria"); // Clube do bolinha!
        for (String temp : result) {
            System.out.println(temp);    
        }

    }

    private static List<String> getFilterOutput(List<String> lines, String filter) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            if (!filter.equals(line)) { 
                result.add(line);
            }
        }
        return result;
    }
}
