package com.acme.cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
	private LocalDate dataNascimento;	
	
	public Cliente(Agencia<Cliente> agencia, String nome, String endereco, LocalDate dataNascimento) {
		super();
		this.agencia = agencia;
		this.nome = nome;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
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
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int compareTo(Cliente outroCliente) {
		return this.getNome().compareTo(outroCliente.getNome());
	}
	
	public Agencia<Cliente> getAgencia() {
		return agencia;
	}
	
	// f(x)
	public static Function <Cliente, Agencia<? extends Cliente>> obtemAgencia = Cliente::getAgencia; 
    
	// g(x)
	public static Function<Agencia<? extends Cliente>, String> obtemCodigoBancoAgencia = Agencia::getCodigoBanco;	
	
	// Funcao composta g(f(x)
    public static Function<Cliente, String> obtemBancoCliente = obtemAgencia.andThen(obtemCodigoBancoAgencia);

    private static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
	public static final Comparator<Cliente> comparadorIdade = 
			(c1, c2) -> c1.getDataNascimento().isBefore(c2.getDataNascimento()) ? 1:-1;		

    
	@Override
	public String toString() {
		return "Nome:" + this.getNome() + 
			   "\nEndereco: " + this.getEndereco() + 
			   "\nData de nascimento: " + this.getDataNascimento().format(formatador);
		
	}
	
}
