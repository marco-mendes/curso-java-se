package com.acme.conta;

import java.time.ZonedDateTime;

import com.acme.agencia.BancoCentral;
import com.acme.agencia.TipoMovimentacao;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;
import com.acme.util.Utils;

/**
 * Classe conta corrente com construcoees basicas em Java
 * 
 * @author Marco Mendes
 * @since 2017
 * 
 *        Pontos para analise: 
 *        - Uso de enums - Uso de excecoees para tratamento de  erros 
 *        - Fatoracao de regras em metodos privativos
 *        - Interfaces fluentes (String Buffer)
 *        - Excecoes no construtor da classe
 * 
 */
public class ContaPoupanca extends Conta {


	public ContaPoupanca(String codigo, double saldo) throws ContaInvalida {		
		super(codigo, saldo);
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
