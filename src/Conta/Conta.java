package Conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;

import Conta.Registros.Registro;

public abstract class Conta implements Serializable{

	private Integer id;
	private String senha, cliente;
	private BigDecimal saldoAtual;
	private LocalDate dataAbertura, dataEncerramento;
	private boolean situacao;
	private LinkedList<Registro> transacoes;
	
	public Conta (Integer id, String senha, String cliente, BigDecimal saldoAtual){
		this.id = id;
		this.senha = senha;
		this.cliente = cliente;
		this.saldoAtual = saldoAtual;
		this.dataAbertura = LocalDate.now();
		this.situacao = true;
		this.transacoes = new LinkedList<>();
	}
	
	

	public Conta(Integer id, String senha, String cliente) {
		this.id = id;
		this.senha = senha;
		this.cliente = cliente;
		this.dataAbertura = LocalDate.now();
		this.situacao = true;
		this.transacoes = new LinkedList<>();
	}



	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public Integer getId() {
		return this.id;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public String getSenha() {
		return senha;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public void setDataEncerramento(LocalDate dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public LocalDate getDataEncerramento() {
		return dataEncerramento;
	}

	public String getCliente() {
		return cliente;
	}

	public LinkedList<Registro> getTransacoes() {
		return transacoes;
	}
	
	
	
}
