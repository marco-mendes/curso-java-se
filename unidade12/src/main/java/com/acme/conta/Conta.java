/**
 * 
 */
package com.acme.conta;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.acme.agencia.BancoCentral;
import com.acme.agencia.TipoMovimentacao;
import com.acme.excecoes.ContaInvalida;
import com.acme.excecoes.LimiteChequeEspecialExcedido;
import com.acme.excecoes.LimiteSaqueExcedido;
import com.acme.excecoes.MovimentacaoInvalida;
import com.acme.util.Utils;

/**
 * @author Marco Mendes
 * @since 2017
 * 
 * Pontos de analise:
 * 
 */
public abstract class Conta {


	private String codigo;
	public double saldo;
	private StatusConta status;
	
	private List<MovimentacaoVO> listaMovimentacoes;
	
	public static final Predicate<Conta> estaNoChequeEspecial = conta -> conta.getSaldo() < 0;  	


	public Conta(String codigo, double saldo) throws ContaInvalida {
		if (!Utils.validaNumeroConta.apply(codigo)) {
			throw new ContaInvalida();
		}
		
		this.codigo = codigo;
		this.saldo = saldo;
		this.status = StatusConta.ATIVA;
		listaMovimentacoes = new ArrayList<MovimentacaoVO>();
		listaMovimentacoes.add(new MovimentacaoVO((TipoMovimentacao.CREDITO), saldo));
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void adicionaMovimentacao(MovimentacaoVO movimento) {
		this.listaMovimentacoes.add(movimento);
	}
	
	public List<MovimentacaoVO> getMovimentacoes() {
		return listaMovimentacoes;
	}
	
	public void creditar(double valor) throws MovimentacaoInvalida {
		verificaValidadeValor(valor);
		this.setSaldo(this.getSaldo() + valor);
		adicionaMovimentacao(new MovimentacaoVO(TipoMovimentacao.CREDITO, valor));
	    BancoCentral.obtemInstanciaBancoCentral().
          registraMovimentacaoBancoCentral(this.getCodigo(), valor, TipoMovimentacao.CREDITO);
	
	}

	public void debitar(double valor) throws MovimentacaoInvalida, LimiteSaqueExcedido, LimiteChequeEspecialExcedido {
		ZonedDateTime horaAgora = ZonedDateTime.now();
		verificaValidadeValor(valor);
		verificaHorarioSaque(valor, horaAgora);
		this.setSaldo(this.getSaldo() - valor);
		adicionaMovimentacao(new MovimentacaoVO(TipoMovimentacao.DEBITO, valor));
		BancoCentral.obtemInstanciaBancoCentral().registraMovimentacaoBancoCentral(this.getCodigo(), valor,
				TipoMovimentacao.DEBITO);

	}

	private void verificaValidadeValor(double valor) throws MovimentacaoInvalida {
		if (valor <= 0) {
			throw new MovimentacaoInvalida("Valor negativo");
		}
	}

	private void verificaHorarioSaque(double valor, ZonedDateTime horaAgora) throws LimiteSaqueExcedido {
		if (valor > 100 && (horaAgora.getHour() >= 23 || horaAgora.getHour() <= 6)) {
			throw new LimiteSaqueExcedido();
		}
	}
	
	public void geraExtrato(EstrategiaImpressao estrategia) {
        estrategia.imprimir(this);
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("  Codigo: ").append(this.getCodigo()).
	       append("  Saldo: ").
	       append(this.getSaldo());
	    
	    return sb.toString();
	}
	
	
}
