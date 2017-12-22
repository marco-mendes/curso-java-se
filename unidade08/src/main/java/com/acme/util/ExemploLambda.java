/**
 * 
 */
package com.acme.util;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ExemploLambda {
	public static void main(String[] args) {
		OperacaoBinaria somador = new OperacaoBinaria() {
			public double soma(double op1, double op2) {
				return op1 + op2;
			}
		};
		
		OperacaoBinaria somador2 = (op1, op2) -> op1 + op2;
		
		somador2.soma(4, 4);
	}

}

@FunctionalInterface
interface OperacaoBinaria {
	double soma(double op1, double op2);
}
