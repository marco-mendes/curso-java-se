/**
 * 
 */
package com.acme.agencia;

import java.util.HashSet;
import java.util.Set;

import com.acme.conta.Conta;

/**
 * Demonstrao do uso do padrão Singleton e inicializao tardia
 * 
 * @author Marco Mendes
 * @since 2017
 */
public class BancoCentral {
	public static final int LIMITE_MINIMO_MONITORACAO_BC = 10000;

	private static BancoCentral bancoCentral;
	private Set<RegistroTransacoesVO> registradorTransacoesAltoValor;

	private BancoCentral() {
		registradorTransacoesAltoValor = new HashSet<RegistroTransacoesVO>();
	}

	public static BancoCentral obtemInstanciaBancoCentral() {
		// Instancia unica - Banco central eh Singleton
		if (bancoCentral == null) {
			// Inicializacao tardia (Lazy Initialization)
			bancoCentral = new BancoCentral();
		}
		return bancoCentral;
	}

	private void adicionaMovimentacao(RegistroTransacoesVO registro) {
		registradorTransacoesAltoValor.add(registro);
	}

	public void registraMovimentacaoBancoCentral(String conta, double valor,
			TipoMovimentacao tipoMovimentacao) {
		if (valor >= BancoCentral.LIMITE_MINIMO_MONITORACAO_BC) {
            // Value Object
			RegistroTransacoesVO registro = 
					new RegistroTransacoesVO("001", conta, tipoMovimentacao, valor);
			
			adicionaMovimentacao(registro);
		}
	}
}
