package com.acme.cliente;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.acme.agencia.Agencia;
import com.acme.excecoes.ContaInvalida;

@RunWith(JUnitPlatform.class)
class ClienteVIPTest {

	Agencia<ClienteVIP> agencia001;
	ClienteRegular maria;
	LocalDate dataNascimentoMaria;

	@BeforeAll
	static void setup()  {
		// Inicializacoes estaticas (executadas apenas uma unica vez
		
	}

	
	@BeforeEach
	void init() throws ContaInvalida {		
	}
	
	
	@Test
	@Tag("TagMarcacaoTestesUnidade")
	@DisplayName("Algum teste do Cliente VIP") 
	void testCreditarRegular()  {
	}
	
	@Ignore
	void testeIgnorado() {
		assertTrue(false);
	}

	@AfterEach
	void tearDown() {
		// Finalizacao depois de um teste.
	}
	 
	@AfterAll
	static void done() {
		// Finalizacao depois de todo o ciclo de teste. 
	}

}
