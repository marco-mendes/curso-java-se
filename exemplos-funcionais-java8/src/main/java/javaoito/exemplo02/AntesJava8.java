
package javaoito.exemplo02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class AntesJava8 {
    public static void main(String[] args) {

        List<String> lines = Arrays.asList("Joao", "Maria", "Jose");
        List<String> resultados = getFilterOutput(lines, "Maria"); // Clube do bolinha!
        for (String resultado : resultados) {
            logger.info(resultado);    
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
    
	private static final Logger logger = Logger.getLogger("Exemplos");
	static {
		logger.setUseParentHandlers(false);
		CustomRecordFormatter formatter = new CustomRecordFormatter();
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.addHandler(consoleHandler);
	}
}
