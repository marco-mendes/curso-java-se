/**
 * 
 */
package com.acme.conta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class EstrategiaImpressaoArquivo implements EstrategiaImpressao {


	public void imprimir(Conta conta) {
	    StringBuilder sb = geraExtratoParaImpressao(conta);
	    
	    Path caminho = Paths.get("./temp-extrato.txt");
	    try {
			Files.write(caminho, sb.toString().getBytes());
		} catch (IOException e) { // Falha na escrita do arquivo
			e.printStackTrace();
		}
	    
	}


}
