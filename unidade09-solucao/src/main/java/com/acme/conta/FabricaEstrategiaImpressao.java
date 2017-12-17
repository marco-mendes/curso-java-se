/**
 * 
 */
package com.acme.conta;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class FabricaEstrategiaImpressao {
    public static EstrategiaImpressao criaEstrategiaImpressao(int tipo) {
    	   if (tipo == 1) {
       	 return new EstrategiaImpressaoTela();
    	   }
    	   else if (tipo ==2) {
    	     return new EstrategiaImpressaoArquivo();
    	   }
    	   throw new RuntimeException("Estrategia de impressão inexistente");
    	     
    }
}
