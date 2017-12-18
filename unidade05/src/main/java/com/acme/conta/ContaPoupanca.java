package com.acme.conta;

import java.time.ZonedDateTime;

import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;
import com.acme.util.Utils;

public class ContaPoupanca implements IConta {

	private String codigo;
	private double saldo;
	private StatusConta status;

	public ContaPoupanca(String codigo, double saldo) throws ContaInvalida {		
		
		if (!Utils.validaNumeroConta(codigo)) {
			throw new ContaInvalida();
		}
		
		this.codigo = codigo;
		this.saldo = saldo;
		this.status = StatusConta.ATIVA;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	    sb.append("Codigo: ").append(this.getCodigo()).
	       append(" Saldo: ").
	       append(this.getSaldo());
	    return sb.toString();
	}

	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido, ContaInvalida {
		ContaPoupanca contaJoao = new ContaPoupanca("12345-6", 1500);
		System.out.println(contaJoao);

		contaJoao.debitar(500);
		System.out.println(contaJoao);

		contaJoao.debitar(2000);
		System.out.println(contaJoao);

	}

}
