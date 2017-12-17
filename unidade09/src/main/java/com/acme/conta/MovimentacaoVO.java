/**
 * 
 */
package com.acme.conta;

import java.time.ZonedDateTime;

import com.acme.agencia.TipoMovimentacao;

/**
 * @author Marco Mendes
 * @since 2017 
 * Padrão de projeto Value Object para criar estruturas de dadas em Java, que não possui Struct
 * 
 */
public class MovimentacaoVO {
	private TipoMovimentacao tipoMovimentacao;
	private double valor;
	private ZonedDateTime momento;
	
	public MovimentacaoVO(TipoMovimentacao tipoMovimentacao, double valor) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		momento = ZonedDateTime.now();
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public double getValor() {
		return valor;
	}
	
	public String toString() {
		return System.lineSeparator() +  
			   " " + momento.toString() +  
			   " " + tipoMovimentacao.name() + 
			   " " + valor;	   
	}

}
