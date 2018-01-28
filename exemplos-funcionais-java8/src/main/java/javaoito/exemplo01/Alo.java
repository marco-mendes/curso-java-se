package javaoito.exemplo01;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.CustomRecordFormatter;

public class Alo {
	private static final Logger logger = Logger.getLogger("Exemplos");
	static {
		logger.setUseParentHandlers(false);
		CustomRecordFormatter formatter = new CustomRecordFormatter();
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(formatter);
		logger.addHandler(consoleHandler);
	}

	public static void main(String[] args) {
		Calculadora soma = (op1, op2) -> op1 + op2;
		Calculadora subtracao = (op1, op2) -> op1 - op2;

		double valor = soma.calcular(4, 8); // 12
		logger.log(Level.INFO, "4 + 8 = {0}", valor);

		valor = subtracao.calcular(4, 8); // -4
		logger.log(Level.INFO, "4 - 8 = {0}", valor);

	}

}

@FunctionalInterface
interface Calculadora {
	public double calcular(double op1, double op2);
}
