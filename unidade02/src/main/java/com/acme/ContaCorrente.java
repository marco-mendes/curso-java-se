package com.acme;

import java.time.ZonedDateTime;

/**
 * Classe conta corrente com construções básica em Java
 * 
 * @author Marco Mendes
 * @since 2017
 * 
 *  Pontos para análise: 
 *  - Uso de enums 
 *  - Uso de exceções para tratamento de erros - Fatoração de regras em métodos privativos
 * 
 */
public class ContaCorrente {

	private double saldo;
	private double limiteCredito;
	private StatusConta status;

	public ContaCorrente(double saldo, double limiteCredito) {
		super();
		this.saldo = saldo;
		this.limiteCredito = limiteCredito;
		this.status = StatusConta.ATIVA;
	}

	public double getSaldo() {
		return saldo;
	}

	private void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	private void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ZonedDateTime horaAgora = ZonedDateTime.now();
		verificaValidadeValor(valor);
		verificaHorarioSaque(valor, horaAgora);
		verificaLimiteChequeEspecial(valor);
		saldo -= valor;
	}

	public void creditar(double valor) throws MovimentacaoInvalida {
		verificaValidadeValor(valor);
		saldo += valor;
	}

	private void verificaValidadeValor(double valor) throws MovimentacaoInvalida {
		if (valor <= 0) {
			throw new MovimentacaoInvalida();
		}
	}

	private void verificaHorarioSaque(double valor, ZonedDateTime horaAgora)
			throws  LimiteSaqueExcedido {

		if (valor > 100 && (horaAgora.getHour() >= 22 || horaAgora.getHour() <= 6)) {
			throw new LimiteSaqueExcedido();
		}
	}

	private void verificaLimiteChequeEspecial(double valor) throws LimiteChequeEspecialExcedido {
		if (valor >= this.getSaldo() + this.getLimiteCredito()) {
			throw new LimiteChequeEspecialExcedido();
		}
	}
	
	@Override
	public String toString() {
		return "Saldo: " + this.getSaldo() + System.lineSeparator() + "Limite do cheque especial: "  + this.getLimiteCredito();
	}

	public static void main(String[] args) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ContaCorrente contaJoao = new ContaCorrente(1500, 2000);
		System.out.println(contaJoao);

		contaJoao.debitar(500);
		System.out.println(contaJoao);
		
		contaJoao.debitar(2000);
		System.out.println(contaJoao);

		contaJoao.debitar(3000);
		System.out.println(contaJoao);
		

	}

}
