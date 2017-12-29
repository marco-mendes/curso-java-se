/**
 * 
 */
package com.acme.util;

import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @author Marco Mendes
 * @since 2017
 */
public interface Utils {
    static final String padraoConta = "\\d\\d\\d\\d\\d-\\d";
    Function<String, Boolean> validaNumeroConta = 
    		numeroConta -> Pattern.matches(padraoConta,numeroConta);
    	
}
