/**
 * 
 */
package com.acme.cliente;

import java.util.HashMap;

import com.acme.agencia.Agencia;
import com.acme.conta.ContaCorrente;
import com.acme.conta.Conta;
import com.acme.excecoes.ContaInexistente;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 *        Pontos para analise - Uso do super - Sobrescrita (override) -
 *        Sobrecarga (overload) - Composicao de objetos - Reuso por delegacaoo -
 *        Reuso por heranca - Programacao por contrato (ou funciona ou retorna
 *        uma excecao) - For Each - Constantes - Demonstracao do uso do Hashmap
 *        (reducao da complexidade de O(N) para O(1))
 * 
 */
public class ClienteVIP extends Cliente {

	/**
	 * 
	 */
	private HashMap<String, Conta> contas;

	public ClienteVIP(String nome, String endereco, String numeroConta) throws ContaInvalida {
		super(nome, endereco);
		contas = new HashMap<String, Conta>();
		contas.put(numeroConta, new ContaCorrente(numeroConta, 0, 5000.0));
	}

	// Overload - Sobrecarga do metodo construtor (nao confundir com override!)
	public ClienteVIP(String nome, String endereco, String numeroConta, double saldo) throws ContaInvalida {
		super(nome, endereco);
		contas = new HashMap<String, Conta>();
		contas.put(numeroConta, new ContaCorrente(numeroConta, saldo, 5000.0));
	}

	@Override // Sobrescrita do metodo da classe pai
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(System.lineSeparator());
		for (Conta conta : contas.values()) {
			sb.append("Dados das contas...");
			sb.append(conta.toString()).append(System.lineSeparator());
		}
		return sb.toString();

	}

	public void creditar(String codigoConta, double valor) throws MovimentacaoInvalida, ContaInexistente {
		Conta conta = contas.get(codigoConta);
		if (conta == null) {
			throw new ContaInexistente();
		}
		conta.creditar(valor);

	}

	public void debitar(String codigoConta, double valor)
			throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido, ContaInexistente {

		Conta conta = contas.get(codigoConta);
		if (conta == null) {
			throw new ContaInexistente();
		}
		conta.debitar(valor);
	}

	public void adiconarNovaConta(String codigo, double saldo) throws ContaInvalida {
		contas.put(codigo, new ContaCorrente(codigo, saldo, 5000.0));
	}

	public double getSaldo() { 
		return contas.values().stream().
				 map(Conta::getSaldo).reduce(0.0, (x, y) -> x + y);
		
		// Explicacao rapida...
		// values() obtem a lista de valores de um mapa das contas (Hashmap)
		// stream() obtem uma sequencia de elementos para processamento serial ou paralelo 

		// map() mapeia um valor para um outro valor
		// map pode ser realizado com funcoes lambda ou referências de metodo
		// map (conta -> conta.getSaldo()) é o uso de interface fluentes
		// map (Conta::getSaldo()) é o uso de referência de métodos
		
		// reduce() é usado para realizar totalizacao de valores
		
		// https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
		
	}
	
	public void tarifaContas()  {
		contas.forEach((chave, conta) -> {
			try {
				conta.debitar(10);
			} catch (MovimentacaoInvalida | LimiteSaqueExcedido | LimiteChequeEspecialExcedido e) {
				e.printStackTrace();
			}
		});
	}


	public static void main(String[] args) throws MovimentacaoInvalida, LimiteSaqueExcedido,
			LimiteChequeEspecialExcedido, ContaInvalida, ContaInexistente {

		ClienteVIP joao = new ClienteVIP("Joao", "Rua das Couves", "12345-6");
		joao.creditar("12345-6", 1300);
		joao.debitar("12345-6", 600);
		System.out.println(joao);

		ClienteVIP maria = new ClienteVIP("Maria", "Rua das Flores", "54321-6", 5000);
		maria.debitar("54321-6", 400);
		maria.adiconarNovaConta("34567-6", 1000);

		maria.adiconarNovaConta("89734-6", 1000);

		
		System.out.println(maria);
		System.out.println("Saldo acumulado das contas da Maria: " + maria.getSaldo());

		Agencia<ClienteVIP> agenciaVIP = new Agencia<ClienteVIP>("");
		agenciaVIP.adicionaCliente(joao);
		agenciaVIP.adicionaCliente(maria);
		ClienteRegular manu = new ClienteRegular("Manu", "Rua da Manu", "54321-6");

		// agenciaVIP.adicionaCliente(manu); Manu nao pode ser adicionada para
		// esta agencia

	}

}
