package javaoito.exemplo01;

import java.util.function.Function;
import java.util.function.BiFunction;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.CustomRecordFormatter;

/**
 * @author Marco Mendes
 * @since 2018
 */
public class NFunction {

	public static void main(String[] args) {
		// Uso da interface Function
		Function<Integer, Integer> dobra = operando -> operando.intValue() * 2;
		logger.log(Level.INFO, "4 x 2 = {0}", dobra.apply(4)); //8

		
		// Uso da interface BiFunction
		BiFunction<Integer, Integer, Integer> soma = (a, b) -> a + b;
		logger.log(Level.INFO, "4 + 2 = {0}", soma.apply(4, 2));

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
