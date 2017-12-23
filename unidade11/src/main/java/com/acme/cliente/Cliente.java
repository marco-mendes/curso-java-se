package com.acme.cliente;

import java.util.function.Function;

import com.acme.agencia.Agencia;
import com.acme.conta.Conta;

/**
 * Classe cliente com construcoes minimas em Java
 * @author Marco Mendes
 * @since Dezembro de 2017
 *
 */
public abstract class Cliente implements Comparable<Cliente>  {
   
	private String nome;
	private String endereco;
	private Agencia<Cliente> agencia;
	
	public Cliente(Agencia agencia, String nome, String endereco) {
		super();
		this.agencia = agencia;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public abstract double getSaldo();
	
	public int compareTo(Cliente outroCliente) {
		return this.getNome().compareTo(outroCliente.getNome());
	}
	
	public Agencia getAgencia() {
		return agencia;
	}
	
	// f(x)
	public static Function <Cliente, Agencia> obtemAgencia = Cliente::getAgencia; 
    
	// g(x)
	public static Function<Agencia, String> obtemCodigoBancoAgencia = Agencia::getCodigoBanco;	
	
	// Funcao composta g(f(x)
    public static Function<Cliente, String> obtemBancoCliente = obtemAgencia.andThen(obtemCodigoBancoAgencia);

	@Override
	public String toString() {
		return "Nome:" + this.getNome() + System.lineSeparator() + "Endereco:" + this.getEndereco();
	}
	
}
