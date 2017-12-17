/**
 * 
 */
package com.acme.conta;

import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

@FunctionalInterface
public interface TransferidorDinheiro {
	
    public void transferir(Conta origem, Conta destino, double valor)
    		throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido;    
       
}
