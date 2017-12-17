/**
 * 
 */
package com.acme;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos para an�lise
 * - Uso do super
 * - Sobrescrita (override)
 * - Sobrecarga (overload)
 * - Composicao de objetos
 * - Reuso por delega��o
 * - Reuso por heran�a
 * - Programacao por contrato (ou funciona ou retorna uma exce��o)
 * 
 */
public class ClienteRegular extends Cliente {

    private ContaCorrente conta; 
	
	public ClienteRegular(String nome, String endereco) {
        this(nome, endereco, 0);
	}
	
	// Overload - Sobrecarga do metodo construtor (nao confundir com override!)
	public ClienteRegular(String nome, String endereco, double saldo) {
		super(nome, endereco);
		conta  = new ContaCorrente(saldo, 0.0);
	}
	
	@Override // Sobrescrita do metodo da classe pai
	public String toString() {
	    return super.toString() + System.lineSeparator() + conta.toString();
	    
	}
	
	public void creditar(double valor) throws MovimentacaoInvalida {
		conta.creditar(valor); // Reuso por delega��o
	}
	
	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		conta.debitar(valor); // Reuso por delega��o
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
