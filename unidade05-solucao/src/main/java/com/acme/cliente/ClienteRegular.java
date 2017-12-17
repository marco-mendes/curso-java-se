/**
 * 
 */
package com.acme.cliente;

import com.acme.conta.ContaCorrente;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos para analise
 * - Uso do super
 * - Sobrescrita (override)
 * - Sobrecarga (overload)
 * - Composicao de objetos
 * - Reuso por delegacao
 * - Programacao por contrato (ou funciona ou retorna uma excecao)
 * 
 */
public class ClienteRegular extends Cliente {

    private ContaCorrente conta; 
	
	public ClienteRegular(String nome, String endereco, String numeroConta) throws ContaInvalida {
		super(nome, endereco);
		conta  = new ContaCorrente(numeroConta, 0.0, 0.0);
	}
	
	// Overload - Sobrecarga do m�todo construtor (nao confundir com override!)
	public ClienteRegular(String nome, String endereco, String numeroConta, double saldo) throws ContaInvalida {
		super(nome, endereco);
		conta  = new ContaCorrente(numeroConta,saldo, 0.0);
	}
	
	@Override // Sobrescrita do metodo da classe pai
	public String toString() {
	    return super.toString() + System.lineSeparator() + conta.toString();
	    
	}
	
	public void creditar(double valor) throws MovimentacaoInvalida {
		conta.creditar(valor); // Reuso por delega��o
	}
	
	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		conta.debitar(valor); // Reuso por delega��o
	}
	
	public double getSaldo() {
		return conta.getSaldo();
	}
	
	public static void main(String[] args) 
			throws MovimentacaoInvalida, LimiteSaqueExcedido, 
			       LimiteChequeEspecialExcedido, ContaInvalida {
		
		ClienteRegular joao = new ClienteRegular("Joao", "Rua das Couves", "12345-6");
		joao.creditar(1300);
		joao.debitar(600);
		System.out.println(joao);
		
		ClienteRegular maria = new ClienteRegular("Maria", "Rua das Flores", "54321-6", 500);
		maria.debitar(400);
		System.out.println(maria);
		
	}

}
