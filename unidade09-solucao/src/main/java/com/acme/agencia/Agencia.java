/**
 * 
 */
package com.acme.agencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.acme.cliente.Cliente;
import com.acme.cliente.ClienteVIP;
import com.acme.excecoes.ContaInexistente;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class Agencia<T extends Cliente> {

	private ArrayList<T> clientes;
	private String nome;

	public Agencia(String nome) {
		this.nome = nome;
		clientes = new ArrayList<T>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionaCliente(T cliente) {
		clientes.add(cliente);
	}

	public double totalizadorMontanteFinanceiro() {
		double montante = 0;
		for (T t : clientes) {
			montante += t.getSaldo();
		}

		return montante;
	}

	public String getCodigoBanco() {
		return "001";
	}
	
	public void ordenaClientesPorNome() {
		Collections.sort(this.clientes);
	}
	 
	public void ordenaClientesPorSaldo() {
		// Java SE 8 - Uso de expressões Lambda 
		Comparator<Cliente> porSaldo =
			(Cliente c1, Cliente c2)->Double.compare(c1.getSaldo(), c2.getSaldo());	
		
		// Java SE 8 - Uso de método Sort na Interface List			
		clientes.sort(porSaldo);	
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T t : clientes) {
			sb.append(t.toString());
		}
		return sb.toString();
	}
	
	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido, 
			       ContaInvalida, ContaInexistente {

		ClienteVIP joao = new ClienteVIP("Joao", "Rua das Couves", "12345-6");
		joao.creditar("12345-6", 1300);
		joao.debitar("12345-6", 600);

		ClienteVIP maria = new ClienteVIP("Maria", "Rua das Flores", "54321-6", 500);
		maria.adiconarNovaConta("34567-6", 1000);
		maria.debitar("54321-6", 400);
		maria.debitar("34567-6", 3000);
		
		ClienteVIP manu = new ClienteVIP("Manu", "Rua da Manu", "56789-6", 500);
		manu.creditar("56789-6", 10000);
				
		
		Agencia<ClienteVIP> agenciaVIP = new Agencia<ClienteVIP>("Agencia 001");
		agenciaVIP.adicionaCliente(joao);
		agenciaVIP.adicionaCliente(maria);
		agenciaVIP.adicionaCliente(manu);
		agenciaVIP.ordenaClientesPorSaldo();
		
		System.out.println(agenciaVIP);
		

		// agenciaVIP.adicionaCliente(manu); Manu nao pode ser adicionada para esta agencia

	}

}

/** Código Java SE 7 - Removido para o uso de expressões lambda
class ComparatorSaldo implements Comparator<Cliente> {

	public int compare(Cliente cliente1, Cliente cliente2) {
		return cliente1.compareTo(cliente2);
	}

}
*/
