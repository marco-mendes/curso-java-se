/**
 * 
 */
package com.acme.cliente;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import com.acme.agencia.Agencia;
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
	
	public ClienteRegular(Agencia agencia, String nome, String endereco, Optional<String> enderecoComercial, String numeroConta, LocalDate dataNascimento) throws ContaInvalida {
		super(agencia, nome, endereco, enderecoComercial, dataNascimento);
		conta  = new ContaCorrente(numeroConta, 0.0, 0.0);
	}
	
	// Overload - Sobrecarga do metodo construtor (nao confundir com override!)
	public ClienteRegular(Agencia agencia, String nome, String endereco, Optional<String> enderecoComercial, String numeroConta, LocalDate dataNascimento, double saldo) throws ContaInvalida {
		super(agencia, nome, endereco, enderecoComercial, dataNascimento);
		conta  = new ContaCorrente(numeroConta,saldo, 0.0);
	}
	
	@Override // Sobrescrita do metodo da classe pai
	public String toString() {
	    return super.toString() + System.lineSeparator() + conta.toString();
	    
	}
	
	public void creditar(double valor) throws MovimentacaoInvalida {
		conta.creditar(valor); // Reuso por delegacao
	}
	
	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		conta.debitar(valor); // Reuso por delegacao
	}
	
	public double getSaldo() {
		return conta.getSaldo();
	}
	
	public static void main(String[] args) 
			throws MovimentacaoInvalida, LimiteSaqueExcedido, 
			       LimiteChequeEspecialExcedido, ContaInvalida {
		
		Agencia<ClienteVIP> agencia001 = 
				new Agencia<ClienteVIP>().
				nome("Agencia Central").
				endereco("Praça Sete de Setembro").
				cep("30000").
				telefone("(31)99999-9999");
		
		LocalDate dataNascimentoJoao = LocalDate.of(1980, Month.DECEMBER, 10);
		ClienteRegular joao = new ClienteRegular(agencia001, "Joao", "Rua das Couves", Optional.empty(), "12345-6", dataNascimentoJoao);
		joao.creditar(1300);
		joao.debitar(600);
		System.out.println(joao);
		
		LocalDate dataNascimentoMaria = LocalDate.of(1974, Month.DECEMBER, 5);
		ClienteRegular maria = new ClienteRegular(agencia001, "Maria", "Rua das Flores", Optional.of("Rua das Flores Comercial"), "54321-6", dataNascimentoMaria, 500);
		maria.debitar(400);
		System.out.println(maria);
		
	}

}
