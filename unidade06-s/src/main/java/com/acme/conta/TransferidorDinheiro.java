/**
 * 
 */
package com.acme.conta;

import com.acme.agencia.BancoCentral;
import com.acme.agencia.RegistroTransacoesVO;
import com.acme.agencia.TipoMovimentacao;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos de analise
 *  - Metodos estaticas
 *  - Construtores privativos
 * 
 */
public class TransferidorDinheiro {
	

	private static final int LIMITE_MINIMO_MONITORACAO_BC = 10000;

	private TransferidorDinheiro() {}
	
    static void transferirEntreContas(IConta origem, IConta destino, double valor) 
    		throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
    	     origem.debitar(valor);
    	     destino.creditar(valor);
    	     if (valor >= LIMITE_MINIMO_MONITORACAO_BC) {
    	       RegistroTransacoesVO  registro = 
    	    		 new RegistroTransacoesVO("001", origem.getCodigo(), 
    	    				                  TipoMovimentacao.TRANSFERENCIA, valor);
    	       
    	       BancoCentral.obtemInstanciaBancoCentral().registraMovimentacao(registro);
    	     }
    }
       
}
