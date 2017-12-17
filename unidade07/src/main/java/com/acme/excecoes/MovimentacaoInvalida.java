/**
 * 
 */
package com.acme.excecoes;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class MovimentacaoInvalida extends Exception {

	private static final long serialVersionUID = -6874371657682163815L;
	public MovimentacaoInvalida(String motivo) {
		super(motivo);
	}
    
}
