/**
 * 
 */
package com.acme.conta;

import java.util.function.Function;

import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos de analise:
 * - Uso de tipos genericos (Generics)
 * 
 */
public class ProcessadorContas<T extends Conta> {
	private T conta;

	public ProcessadorContas(T conta) {
		this.conta = conta;
	}
	
	Function<ContaCorrente, Double> calcularTarifaMensal = contaOrigem -> Double.valueOf(20);
	Function<ContaPoupanca, Double> calcularReajuste = contaOrigem -> Double.valueOf(contaOrigem.getSaldo()*0.06);
	
	public void reajustarConta() throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		if (conta instanceof ContaCorrente) {
			calcularTarifaMensal.apply((ContaCorrente)conta);
		} else if (conta instanceof ContaPoupanca) {
			calcularReajuste.apply((ContaPoupanca)conta);
		}
	}

	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido, ContaInvalida {
		ContaPoupanca contaPoupancaJoao = new ContaPoupanca("12345-6", 1500);
		ProcessadorContas<ContaPoupanca> calculadorCP = 
				new ProcessadorContas<ContaPoupanca>(contaPoupancaJoao);
		calculadorCP.reajustarConta();
		
		System.out.println("Dados da poupanca do Joao");
		System.out.println(contaPoupancaJoao);

		ContaCorrente contaCorrenteJoao = new ContaCorrente("PRINCIPAL", 1500, 0);
		ProcessadorContas<ContaCorrente> calculadorCC = new ProcessadorContas<ContaCorrente>(contaCorrenteJoao);
		calculadorCC.reajustarConta();
		System.out.println("Dados da conta corrente do Joao");
		System.out.println(contaCorrenteJoao);

	}

}
