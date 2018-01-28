package javaoito.exemplo07;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import util.CustomRecordFormatter;


class Tarefa implements Runnable {
	public Tarefa(Logger logger) {
		logger.info("Execução de alguma tarefa no método close...");
	}

	public void run() {
		// Alguma implementação 
	}
	
}

public class ReusoStreams {
	
	
	public static void main(String[] args) {
		String[] array = { "Joao", "Maria", "Maria", "Manoela", "Joaquina" };
		Stream<String> stream = Arrays.stream(array);

		// Loop em uma stream com uso do método onClose
		logger.info("\nPercorrendo a stream:");
		stream.onClose(new Tarefa(logger)).forEach(logger::info);

	
		// Com a interface Supplier
		Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);

		// Uso da stream com o supplier
		logger.info("\nPercorrendo uma stream com supplier:");
		streamSupplier.get().forEach(logger::info);

		// Obtenção de uma nova stream com o supplier
		logger.info("\nReuso de uma stream:");
		long contador = streamSupplier.get().filter("Maria"::equals).count();
		logger.log(Level.INFO, "{0}", contador);

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






