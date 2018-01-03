/**
 * 
 */
package com.acme.excecoes;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class ContaInvalida extends Exception {

	private static final long serialVersionUID = -7544626576304158580L;
	
	@Override
	public String getMessage() {
		return "Conta Invalida";
	}

}
