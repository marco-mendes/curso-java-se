/**
 * 
 */
package com.acme.conta;

import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos de análise:
 * - Uso de interfaces
 * 
 */
public interface IConta {
	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido;
    public void creditar (double valor) throws MovimentacaoInvalida;
    public String getNome();
    public double getSaldo();
}
