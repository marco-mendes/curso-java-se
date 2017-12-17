/**
 * 
 */
package com.acme.conta;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class EstrategiaImpressaoTela implements EstrategiaImpressao {


	public void imprimir(Conta conta) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Extrato da conta:").append(System.lineSeparator());
	    sb.append("Codigo: ").append(conta.getCodigo());
	    
	    for (MovimentacaoVO movimentacao : conta.getMovimentacoes()) {
	    		sb.append(movimentacao);
	    }
	    sb.append(System.lineSeparator());
	    sb.append(" Saldo: ").
	       append(conta.getSaldo());
	
	    System.out.println(sb.toString());
	}

	/* (non-Javadoc)
	 * @see com.acme.conta.EstrategiaImpressao#geraExtratoParaImpressao(com.acme.conta.Conta)
	 */
	public StringBuilder geraExtratoParaImpressao(Conta conta) {
		return null;
	}

}
