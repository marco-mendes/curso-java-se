/**
 * 
 */
package com.acme.conta;

/**
 * @author Marco Mendes
 * @since 2017
 * Contrato padrão de estrategia de impressão (Padrão Strategy)
 */
public interface EstrategiaImpressao {
    public void imprimir(Conta conta);
}
