/**
 * 
 */
package com.acme.agencia;

import java.util.ArrayList;
import com.acme.cliente.Cliente;

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

}
