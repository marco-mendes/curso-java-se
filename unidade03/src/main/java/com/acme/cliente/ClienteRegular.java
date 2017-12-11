/**
 * 
 */
package com.acme.cliente;

import com.acme.conta.ContaCorrente;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos para análise
 * - Uso do super
 * - Sobrescrita (override)
 * - Sobrecarga (overload)
 * - Composicao de objetos
 * - Reuso por delegação
 * - Programacao por contrato (ou funciona ou retorna uma exceção)
 * 
 */
public class ClienteRegular extends Cliente {

    private ContaCorrente conta; 
	
	public ClienteRegular(String nome, String endereco) {
		super(nome, endereco);
		conta  = new ContaCorrente("PRINCIPAL", 0.0, 0.0);
	}
	
	// Overload - Sobrecarga do método construtor (nao confundir com override!)
	public ClienteRegular(String nome, String endereco, double saldo) {
		super(nome, endereco);
		conta  = new ContaCorrente("PRINCIPAL",saldo, 0.0);
	}
	
	@Override // Sobrescrita do metodo da classe pai
	public String toString() {
	    return super.toString() + System.lineSeparator() + conta.toString();
	    
	}
	
	public void creditar(double valor) throws MovimentacaoInvalida {
		conta.creditar(valor); // Reuso por delegação
	}
	
	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		conta.debitar(valor); // Reuso por delegação
	}
	
	public static void main(String[] args) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ClienteRegular joao = new ClienteRegular("Joao", "Rua das Couves");
		joao.creditar(1300);
		joao.debitar(600);
		System.out.println(joao);
		
		ClienteRegular maria = new ClienteRegular("Maria", "Rua das Flores", 500);
		maria.debitar(400);
		System.out.println(maria);
		
	}

}
