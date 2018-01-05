package com.acme.cliente;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.acme.agencia.Agencia;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

@RunWith(JUnitPlatform.class)
class ClienteRegularTest {

	Agencia<ClienteVIP> agencia001;
	ClienteRegular maria;
	LocalDate dataNascimentoMaria;

	@BeforeAll
	static void setup()  {
		// Inicializacoes estaticas (executadas apenas uma unica vez
		
	}

	
	@BeforeEach
	void init() throws ContaInvalida {	
		dataNascimentoMaria = LocalDate.of(1974, Month.DECEMBER, 5);
		agencia001 = 
				new Agencia<ClienteVIP>().
				nome("Agencia Central").
				endereco("Praça Sete de Setembro").
				cep("30000").
				telefone("(31)99999-9999");
		
		maria = new ClienteRegular(agencia001, 
				"Maria", "Rua das Flores", Optional.of("Rua das Flores Comercial"), 
				"54321-6", dataNascimentoMaria, 500);
		
	}
	
	
	@Test
	@Tag("Smoke")
	@DisplayName("Teste de credito regular") 
	void testCreditarRegular() throws ContaInvalida, MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		maria.creditar(400);		
		assertEquals(900, maria.getSaldo());
	}

	@Test
	@DisplayName("Teste de debito regular") 
	void testDebitarRegular() throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		maria.debitar(400);		
		assertEquals(100, maria.getSaldo());
	}
	
	@Test
	@DisplayName("Criacao de cliente com conta invalida.") 
	void testeDebitoIrregular() throws ContaInvalida {
		
	    Throwable exception = 
	    		assertThrows(ContaInvalida.class, 
	    			() -> {
					        maria = new ClienteRegular(agencia001, 
							"Maria", "Rua das Flores", Optional.of("Rua das Flores Comercial"), 
							"54321-65", dataNascimentoMaria, 100);
	    				  }
	    	
	    	);
	    assertEquals("Conta Invalida", exception.getMessage());
	}
	 
	@Test
	@DisplayName("Criacao de cliente com conta invalida. Alternativa.") 
	void testeDebitoIrregular2() {
	    assertThrows(ContaInvalida.class, () -> {
			maria = new ClienteRegular(agencia001, 
					"Maria", "Rua das Flores", Optional.of("Rua das Flores Comercial"), 
					"54321-36", dataNascimentoMaria, 100);
	    });
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
