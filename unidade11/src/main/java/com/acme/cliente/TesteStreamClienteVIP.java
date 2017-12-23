/**
 * 
 */
package com.acme.cliente;

import com.acme.agencia.Agencia;
import com.acme.excecoes.ContaInexistente;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;

/**
 * @author Marco Mendes
 * @since 2017
 */
public class TesteStreamClienteVIP {
	public static void main(String[] args) {
		// Uso de interface fluente
		try {
			Agencia<ClienteVIP> agencia001 = 
					new Agencia<ClienteVIP>().
					nome("Agencia Central").
					endereco("Praça Sete de Setembro").
					cep("30000").
					telefone("(31)99999-9999");
			
			ClienteVIP maria = new ClienteVIP(agencia001, "Maria", "Rua das Flores", "53321-6", 500);
			maria.debitar("53321-6", 400);
			maria.adiconarNovaConta("34567-6", 1000);
			maria.debitar("34567-6", 3000);
			
			System.out.println(maria);		
			System.out.println("Banco da Maria: " + Cliente.obtemBancoCliente.apply(maria));
			
			System.out.println("\nNumero total das contas");
			System.out.println(ClienteVIP.obtemTotalContas.apply(maria));
			System.out.println("\nEstatistica das contas");
			System.out.println(ClienteVIP.obtemEstatisticaSaldoContas.apply(maria));
			System.out.println("\nConta com maior saldo");
			System.out.println(ClienteVIP.obtemContaMaiorSaldo.apply(maria));
			System.out.println("\nConta com menor saldo");
			System.out.println(ClienteVIP.obtemContaMenorSaldo.apply(maria));
			System.out.println("\nSaldo medio das contas");
			System.out.println(ClienteVIP.obtemSaldoMedioContas.apply(maria));
			System.out.println("\nSaldo total das contas");
			System.out.println(ClienteVIP.obtemSaldoTotalContas.apply(maria));
			System.out.println("\nContas no cheque especial");
			System.out.println(ClienteVIP.obtemContasNoChequeEspecial.apply(maria));
		
		
		
		} catch (ContaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MovimentacaoInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LimiteSaqueExcedido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LimiteChequeEspecialExcedido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContaInexistente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
