package com.acme.conta;

import com.acme.agencia.BancoCentral;
import com.acme.agencia.TipoMovimentacao;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * Classe conta corrente com construcoes basicas em Java
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
public class ContaCorrente extends Conta {

	private double limiteCredito;
	
	public ContaCorrente(String codigo, double saldo, double limiteCredito) throws ContaInvalida {		
        super(codigo, saldo);
		this.limiteCredito = limiteCredito;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	private void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Override
	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		verificaLimiteChequeEspecial(valor);
		super.debitar(valor);
	}

	private void verificaLimiteChequeEspecial(double valor) throws LimiteChequeEspecialExcedido {
		if (valor >= this.getSaldo() + this.getLimiteCredito()) {
			throw new LimiteChequeEspecialExcedido();
		}
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(super.toString());
	    sb.append(" Limite especial: ").
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
		
		ContaCorrente contaMaria = new ContaCorrente("12346-7", 0, 2000);
		
		contaJoao.geraExtrato(FabricaEstrategiaImpressao.criaEstrategiaImpressao(2));

		ProcessadorContas tarifador = (Conta conta) -> conta.debitar(10);				
		tarifador.processaConta(contaJoao);
	
		// Função lambda com multiplos argumentos e multiplas linhas
		TransferidorDinheiro transferidor = 
				(Conta origem, Conta destino, double valor) -> 
		{
			origem.debitar(valor);
			destino.creditar(valor);
			BancoCentral.obtemInstanciaBancoCentral().
			registraMovimentacaoBancoCentral(origem.getCodigo(), valor, TipoMovimentacao.TRANSFERENCIA);
		};

		System.out.println("\nConta da Maria antes da ajuda do Joao...");
		System.out.println(contaMaria);
		transferidor.transferir(contaJoao, contaMaria, 100);
		System.out.println("\nDepois da ajuda do Joao...");
		System.out.println(contaMaria);

	}

}
