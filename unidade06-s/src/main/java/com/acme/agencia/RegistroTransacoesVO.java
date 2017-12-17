/**
 * 
 */
package com.acme.agencia;

import java.time.ZonedDateTime;

/**
 * @author Marco Mendes
 * @since 2017 Padrão de projeto Value Object para criar estruturas de dadas em
 *        Java, que não possui Struct
 * 
 */
public class RegistroTransacoesVO {
	private String banco;
	private String conta;
	private TipoMovimentacao tipoMovimentacao;
	private double valor;
	private ZonedDateTime momento;

	public RegistroTransacoesVO(String banco, String conta, TipoMovimentacao tipoMovimentacao, double valor) {
		this.banco = banco;
		this.conta = conta;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		momento = ZonedDateTime.now();
	}

	public String getBanco() {
		return banco;
	}

	public String getConta() {
		return conta;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public double getValor() {
		return valor;
	}

	public ZonedDateTime getMomento() {
		return this.getMomento();
	}

}
