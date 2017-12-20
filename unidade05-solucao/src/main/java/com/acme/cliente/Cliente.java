package com.acme.cliente;

import com.acme.agencia.Agencia;

/**
 * Classe cliente com construcoes minimas em Java
 * @author Marco Mendes
 * @since Dezembro de 2017
 *
 * Pontos de analise
 * - Definicaoo de classes
 * - Definicaoo de atributos
 * - Construtores
 * - Sobrescrita de metodos
 * - Criacaoo de objetos
 * - Classe abstrata
 * - Tipos genericos
 *
 */
public abstract class Cliente implements Comparable<Cliente>  {
   
	private String nome;
	private String endereco;
	
	public Cliente(String nome, String endereco) {
		super();
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
	
	private int estrategia = 1;
	public void setEstrategia(int estrategia) {
		this.estrategia = estrategia;
	}
	
	public int compareTo(Cliente outroCliente) {
		if (estrategia ==1) {
		   return this.getNome().compareTo(outroCliente.getNome());
		} 
		
		return (Double.compare(this.getSaldo(), outroCliente.getSaldo()));
		
	}

	@Override
	public String toString() {
		return "Nome:" + this.getNome() + System.lineSeparator() + "Endereco:" + this.getEndereco();
	}
	
	
}
