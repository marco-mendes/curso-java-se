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

    /** Uso de métodos default do Java SE 8 */
	public default StringBuilder geraExtratoParaImpressao(Conta conta) {
		StringBuilder sb = new StringBuilder();
	    sb.append("Extrato da conta:").append(System.lineSeparator());
	    sb.append("Codigo: ").append(conta.getCodigo());
	    
	    for (MovimentacaoVO movimentacao : conta.getMovimentacoes()) {
	    		sb.append(movimentacao);
	    }
	    sb.append(System.lineSeparator());
	    sb.append(" Saldo: ").
	       append(conta.getSaldo());
		return sb;
	}
}
