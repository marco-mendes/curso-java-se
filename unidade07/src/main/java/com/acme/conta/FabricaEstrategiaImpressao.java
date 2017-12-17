/**
 * 
 */
package com.acme.conta;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class FabricaEstrategiaImpressao {
	private FabricaEstrategiaImpressao() {}
    public static EstrategiaImpressao criaEstrategiaImpressao(int tipo) {
    	   if (tipo == 1) {
       	 return new EstrategiaImpressaoTela();
    	   }

    	   throw new RuntimeException("Estrategia de impressão inexistente");
    	     
    }
}
