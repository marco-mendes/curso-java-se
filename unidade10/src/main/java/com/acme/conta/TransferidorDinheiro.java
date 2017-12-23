/**
 * 
 */
package com.acme.conta;

import com.acme.agencia.BancoCentral;
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
public interface TransferidorDinheiro {
	
    static void transferirEntreContas(Conta origem, Conta destino, double valor) 
    		throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
    	     origem.debitar(valor);
    	     destino.creditar(valor);
    	     BancoCentral.obtemInstanciaBancoCentral().
    	         registraMovimentacaoBancoCentral(origem.getCodigo(), valor, TipoMovimentacao.TRANSFERENCIA);
    	     
    }
       
}
