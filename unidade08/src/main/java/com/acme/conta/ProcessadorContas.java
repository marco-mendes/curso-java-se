package com.acme.conta;

import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 

 */
@FunctionalInterface
public interface ProcessadorContas {
	public void processaConta(Conta conta) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido;
}

/*
Uma interface funcional é qualquer interface que contenha apenas um método abstrato. 
(Uma interface funcional pode conter um ou mais métodos padrão ou métodos estáticos)
*/
