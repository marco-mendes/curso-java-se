/**
 * 
 */
package com.acme.agencia;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.acme.cliente.Cliente;
import com.acme.cliente.ClienteVIP;
import com.acme.conta.Conta;
import com.acme.excecoes.ContaInexistente;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

public class Agencia<T extends Cliente> {

	/**
	 * 
	 */
	private static final int MELHOR_IDADE = 65;
	private ArrayList<T> clientes;
	private String nome;
	private String endereco;
	private String cep;
	private String telefone;


	public Agencia() {
		clientes = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public Agencia<T> nome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Agencia<T> endereco(String endereco) {
		this.endereco = endereco;
		return this;
	}
	
	public Agencia<T> cep(String cep) {
		this.cep = cep;
		return this;
	}
	
	public Agencia<T> telefone(String telefone) {
		this.telefone = telefone;
		return this;
	}
	

	public void adicionaCliente(T cliente) {
		clientes.add(cliente);
	}
	
	public static final Function <Agencia<? extends Cliente>, Double> totalizadorMontanteFinanceiro =
	 agencia -> agencia.clientes.stream().
	   mapToDouble(cliente -> cliente.getSaldo()).
	   reduce(0.0, (a,b) -> a+b); 

	public void ordenaClientesPorNome() {
		Collections.sort(this.clientes);
	}
	
	public String getCodigoBanco() {
		return "001";
	}
	 
	public void ordenaClientesPorSaldo() {
		// Sort utiliza o padrao de desenho GoT Strategy, i.e, podemos variar dinamicamente a estrategia de ordenacao
		Collections.sort(this.clientes, new ComparatorSaldo());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T t : clientes) {
			sb.append(t.toString());
		}
		return sb.toString();
	}
	
	public void imprimirClientesPorIdade() {
		clientes.stream().sorted(Cliente.comparadorIdade).forEach(System.out::println);
	}
	
	Predicate<Cliente> universitarios = cliente -> { 
	      int anos = LocalDate.now().getYear() - cliente.getDataNascimento().getYear();
	      return anos >=16 && anos <=30;
	};
	
	public void imprimirClientesUniversitarios() {
		clientes.stream().filter(universitarios).forEach(System.out::println);
	}
	
	Predicate<Cliente> melhorIdade = cliente -> 
        (LocalDate.now().getYear() - cliente.getDataNascimento().getYear()) >= MELHOR_IDADE;

	public void imprimirClientesMelhorIdade() {
	clientes.stream().filter(melhorIdade).forEach(System.out::println);
	}	
	
	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido, 
			       ContaInvalida, ContaInexistente {

		// Uso do padrão interface fluente
		Agencia<Cliente> agencia001 = 
				new Agencia<Cliente>().
				nome("001").
				endereco("Praça Sete de Setembro").
				cep("30000").
				telefone("(31)99999-9999");

		LocalDate dataNascimentoJoao = LocalDate.of(1987, Month.DECEMBER, 5);
		ClienteVIP joao = new ClienteVIP(agencia001, "Joao", "Rua das Couves", Optional.empty(), "12345-6", dataNascimentoJoao);
		joao.creditar("12345-6", 1300);
		joao.debitar("12345-6", 600);

		LocalDate dataNascimentoMaria = LocalDate.of(1974, Month.DECEMBER, 5);
		ClienteVIP maria = new ClienteVIP(agencia001, "Maria", "Rua das Flores", Optional.empty(), "54321-6", dataNascimentoMaria, 500);
		maria.adiconarNovaConta("34567-6", 1000);
		maria.debitar("54321-6", 400);
		maria.debitar("34567-6", 3000);

		LocalDate dataNascimentoManu = LocalDate.of(1992, Month.JULY, 21);
		ClienteVIP manu = new ClienteVIP(agencia001, "Manu", "Rua da Manu", Optional.empty(), "56789-6", dataNascimentoManu, 500);
		manu.creditar("56789-6", 10000);
		
		LocalDate dataNascimentoMatusalem = LocalDate.of(1756, Month.JULY, 13);
		ClienteVIP matusalem = new ClienteVIP(agencia001, "Matusa", "Rua do seu Matusa", Optional.of("Lojas do seu Matusa"), "56739-6", dataNascimentoMatusalem, 500);				
		
		
		agencia001.adicionaCliente(joao);
		agencia001.adicionaCliente(maria);
		agencia001.adicionaCliente(manu);
		agencia001.adicionaCliente(matusalem);
		
		System.out.println(agencia001);
		

		// agenciaVIP.adicionaCliente(manu); Manu nao pode ser adicionada para esta agencia

		System.out.println("Montante da agencia");
		System.out.println(totalizadorMontanteFinanceiro.apply(agencia001));
		
		System.out.println("\n\nClientes ordenados por idade");
		agencia001.imprimirClientesPorIdade();

		System.out.println("\n\nClientes da melhor idade");
		agencia001.imprimirClientesMelhorIdade();
		
		System.out.println("\n\nClientes universitarios");
		agencia001.imprimirClientesUniversitarios();
		
	}

}

class ComparatorSaldo implements Comparator<Cliente> {

	public int compare(Cliente cliente1, Cliente cliente2) {
		return cliente1.compareTo(cliente2);
	}

}
