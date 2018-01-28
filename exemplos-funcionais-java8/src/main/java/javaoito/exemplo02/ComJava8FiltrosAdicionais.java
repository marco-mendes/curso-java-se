/**
 * 
 */
package javaoito.exemplo02;

import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2018
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

        
        logger.log(Level.INFO, "{0}", maria);

        Person carolina = persons.stream()
                .filter(x -> "Carolina".equals(x.getName()))
                .findAny()
                .orElse(null);

        logger.log(Level.INFO, "{0}", carolina);
       
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

        logger.log(Level.INFO, "{0}", manoela);

        //Forma alternativa de escrita (mais verbosa)
        manoela = persons.stream()
                .filter(p -> {
                    if ("Manoela".equals(p.getName()) && 30 == p.getAge()) {
                        return true;
                    }
                    return false;
                }).findAny()
                .orElse(null);

        logger.log(Level.INFO, "{0}", manoela);
       
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
