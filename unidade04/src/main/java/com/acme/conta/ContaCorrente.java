package com.acme.conta;

import java.time.ZonedDateTime;

import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * Classe conta corrente com constru��es b�sica em Java
 * 
 * @author Marco Mendes
 * @since 2017
 * 
 *        Pontos para analise: 
 *        - Uso de enums - Uso de excecoes para tratamento de  erros 
 *        - Fatoracao de regras em metodos privativos
 *        - Interfaces fluentes (String Buffer)
 * 
 */
public class ContaCorrente implements IConta {

	private String nome;
	private double saldo;
	private double limiteCredito;
	private StatusConta status;

	public ContaCorrente(String nome, double saldo, double limiteCredito) {		
		this.nome = nome;
		this.saldo = saldo;
		this.limiteCredito = limiteCredito;
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

	private void verificaLimiteChequeEspecial(double valor) throws LimiteChequeEspecialExcedido {
		if (valor >= this.getSaldo() + this.getLimiteCredito()) {
			throw new LimiteChequeEspecialExcedido();
		}
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Nome: ").append(this.getNome()).
	       append(" Saldo: ").
	       append(this.getSaldo()).
	       append(" Limite especial: ").
	       append(this.getLimiteCredito());
	    return sb.toString();
	}

	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ContaCorrente contaJoao = new ContaCorrente("PRINCIPAL", 1500, 2000);
		System.out.println(contaJoao);

		contaJoao.debitar(500);
		System.out.println(contaJoao);

		contaJoao.debitar(2000);
		System.out.println(contaJoao);

	}

}
