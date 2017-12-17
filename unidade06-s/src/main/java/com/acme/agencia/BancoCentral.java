/**
 * 
 */
package com.acme.agencia;

import java.util.HashSet;
import java.util.Set;

/**
 * Demonstrao do uso do padrão Singleton e inicializao tardia
 * 
 * @author Marco Mendes
 * @since 2017
 */
public class BancoCentral {
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

	public void registraMovimentacao(RegistroTransacoesVO registro) {
		registradorTransacoesAltoValor.add(registro);
	}
}
