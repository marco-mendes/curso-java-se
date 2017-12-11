package com.acme.conta;

import java.time.ZonedDateTime;

import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * Classe conta corrente com construções básica em Java
 * 
 * @author Marco Mendes
 * @since 2017
 * 
 *        Pontos para análise: 
 *        - Uso de enums - Uso de exceções para tratamento de  erros 
 *        - Fatoração de regras em métodos privativos
 *        - Interfaces fluentes (String Buffer)
 * 
 */
public class ContaPoupanca implements IConta {

	private String nome;
	private double saldo;
	private StatusConta status;

	public ContaPoupanca(String nome, double saldo) {		
		this.nome = nome;
		this.saldo = saldo;
		this.status = StatusConta.ATIVA;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	private void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ZonedDateTime horaAgora = ZonedDateTime.now();
		verificaValidadeValor(valor);
		verificaHorarioSaque(valor, horaAgora);
		this.setSaldo(this.getSaldo() - valor);
	}

	public void creditar(double valor) throws MovimentacaoInvalida {
		verificaValidadeValor(valor);
		saldo += valor;
	}

	private void verificaValidadeValor(double valor) throws MovimentacaoInvalida {
		if (valor <= 0) {
			throw new MovimentacaoInvalida("Valor negativo");
		}
	}

	private void verificaHorarioSaque(double valor, ZonedDateTime horaAgora) throws LimiteSaqueExcedido {

		if (valor > 100 && (horaAgora.getHour() >= 22 || horaAgora.getHour() <= 6)) {
			throw new LimiteSaqueExcedido();
		}
	}


	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Nome: ").append(this.getNome()).
	       append(" Saldo: ").
	       append(this.getSaldo());
	    return sb.toString();
	}

	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ContaPoupanca contaJoao = new ContaPoupanca("PRINCIPAL", 1500);
		System.out.println(contaJoao);

		contaJoao.debitar(500);
		System.out.println(contaJoao);

		contaJoao.debitar(2000);
		System.out.println(contaJoao);

	}

}
