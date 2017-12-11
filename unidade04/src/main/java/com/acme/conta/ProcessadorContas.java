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
 * - Uso de tipos genéricos (Generics)
 * 
 */
public class ProcessadorContas<T extends IConta> {
	private T conta;

	public ProcessadorContas(T conta) {
		this.conta = conta;
	}

	public void reajustarConta() throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		if (conta instanceof ContaCorrente) {
			conta.debitar(10); // debito de 10 reais
		} else if (conta instanceof ContaPoupanca) {
			conta.creditar(((ContaPoupanca) conta).getSaldo() * 0.01);
		}
	}

	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ContaPoupanca contaPoupancaJoao = new ContaPoupanca("PRINCIPAL", 1500);
		ProcessadorContas<ContaPoupanca> calculadorCP = new ProcessadorContas<ContaPoupanca>(contaPoupancaJoao);
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
