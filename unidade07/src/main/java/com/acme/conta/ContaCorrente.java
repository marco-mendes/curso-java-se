package com.acme.conta;

import java.nio.file.Path;
import java.time.ZonedDateTime;

import com.acme.agencia.BancoCentral;
import com.acme.agencia.TipoMovimentacao;
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
		
		contaJoao.geraExtrato(FabricaEstrategiaImpressao.criaEstrategiaImpressao(1));

	}

}
