/**
 * 
 */
package com.acme;

import java.util.ArrayList;
import java.util.List;

import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 *        Pontos para análise 
 *        - Uso do super 
 *        - Sobrescrita (override) 
 *        - Sobrecarga (overload) 
 *        - Composicao de objetos 
 *        - Reuso por delegação 
 *        - Reuso por herança
 *        - Programacao por contrato (ou funciona ou retorna uma exceção) 
 *        - TiposGenericos 
 *        - For Each
 *        - Constantes
 * 
 */
public class ClienteVIP extends Cliente {

	/**
	 * 
	 */
	private static final String PRINCIPAL = "PRINCIPAL";
	private List<ContaCorrente> contas;

	public ClienteVIP(String nome, String endereco) {
		super(nome, endereco);
		contas = new ArrayList<ContaCorrente>();
		contas.add(new ContaCorrente(PRINCIPAL, 0, 5000.0));
	}

	// Overload - Sobrecarga do método construtor (nao confundir com override!)
	public ClienteVIP(String nome, String endereco, double saldo) {
		super(nome, endereco);
		contas = new ArrayList<ContaCorrente>();
		contas.add(new ContaCorrente(PRINCIPAL, saldo, 5000.0));
	}

	@Override // Sobrescrita do metodo da classe pai
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(System.lineSeparator());
		for (ContaCorrente conta : contas) {
			sb.append("Dados das contas...");
			sb.append(conta.toString()).append(System.lineSeparator());
		}
		return sb.toString();

	}

	public void creditar(String nome, double valor) throws MovimentacaoInvalida {
		for (ContaCorrente conta : contas) {
			if (conta.getNome().equals(nome)) {
				conta.creditar(valor); // Reuso por delegação
			}
		}
	}

	public void debitar(String nome, double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		for (ContaCorrente conta:contas) {
			if (conta.getNome().equals(nome)) {
				conta.debitar(valor); // Reuso por delegação
			}
		}
	}
	
	public void adiconarNovaConta(String nome, double saldo) {
		contas.add(new ContaCorrente(nome, saldo, 5000.0));
	}

	public static void main(String[] args)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ClienteVIP joao = new ClienteVIP("Joao", "Rua das Couves");
		joao.creditar(PRINCIPAL, 1300);
		joao.debitar(PRINCIPAL, 600);
		System.out.println(joao);

		ClienteVIP maria = new ClienteVIP("Maria", "Rua das Flores", 500);
		maria.debitar(PRINCIPAL, 400);
		maria.adiconarNovaConta("SECUNDARIA", 1000);
		maria.debitar("SECUNDARIA", 3000);
		System.out.println(maria);

	}

}
