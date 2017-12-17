package com.acme.conta;

import java.time.ZonedDateTime;

import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;
import com.acme.util.Utils;

/**
 * Classe conta corrente com construcoes basicas em Java
 * 
 * @author Marco Mendes
 * @since 2017
 * 
 *        Pontos para analise: 
 *        - Uso de enums - Uso de excecoes para tratamento de  erros 
 *        - Fatoracaoo de regras em metodos privativos
 *        - Interfaces fluentes (String Buffer)
 * 
 */
public class ContaCorrente implements IConta {

	private String codigo;
	private double saldo;
	private double limiteCredito;
	private StatusConta status;

	public ContaCorrente(String codigo, double saldo, double limiteCredito) throws ContaInvalida {		
		if (!Utils.validaNumeroConta(codigo)) {
			throw new ContaInvalida();
		}
		this.codigo = codigo;
		this.saldo = saldo;
		this.limiteCredito = limiteCredito;
		this.status = StatusConta.ATIVA;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setNome(String codigo) {
		this.codigo = codigo;
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
	    sb.append("Codigo: ").append(this.getCodigo()).
	       append(" Saldo: ").
	       append(this.getSaldo()).
	       append(" Limite especial: ").
	       append(this.getLimiteCredito());
	    return sb.toString();
	}

	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido, ContaInvalida {
		ContaCorrente contaJoao = new ContaCorrente("12345-6", 1500, 2000);
		System.out.println(contaJoao);

		contaJoao.debitar(500);
		System.out.println(contaJoao);

		contaJoao.debitar(2000);
		System.out.println(contaJoao);

	}

}
