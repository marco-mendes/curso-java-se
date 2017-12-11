/**
 * 
 */
package com.acme.util;

import java.util.regex.Pattern;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class Utils {
    private static String padraoConta = "\\d\\d\\d\\d\\d-\\d";
    private Utils() {}

	public static boolean validaNumeroConta(String numeroConta) {
	      return Pattern.matches(padraoConta,numeroConta);
   }
}
