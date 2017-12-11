package com.acme;

/**
 * Classe conta corrente com construções básica em Java
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos para análise:
 *  - Uso de enums
 * 
 */
public class ContaCorrente {

	private double saldo;
	private double limiteCredito;
	private StatusConta status;
	
	public ContaCorrente(double saldo, double limiteCredito) {
		super();
		this.saldo = saldo;
		this.limiteCredito = limiteCredito;
		this.status = StatusConta.ATIVA;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}	

	
}
