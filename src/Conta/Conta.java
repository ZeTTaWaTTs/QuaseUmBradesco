package Conta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;

import Conta.Registros.ListaMovimentos;
import Conta.Registros.RegistraMovimento;

public abstract class Conta implements Serializable{

	private Integer id;
	private String senha, cliente;
	private BigDecimal saldoAtual;
	private LocalDate dataAbertura, dataEncerramento;
	private boolean situacao;
	private ListaMovimentos<RegistraMovimento> transacoes;
	
	public Conta (Integer id, String senha, String cliente, BigDecimal saldoAtual){
		this.id = id;
		this.senha = senha;
		this.cliente = cliente;
		this.saldoAtual = saldoAtual;
		this.dataAbertura = LocalDate.now();
		this.situacao = true;
		this.transacoes = new ListaMovimentos();
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
		//salvaSaldo();
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

	public ListaMovimentos getTransacoes() {
		return transacoes;
	}
	
	/*private void salvaSaldo() {
		FileWriter arq;

		try {
			arq = new FileWriter(this.cliente+"."+this.id);
			PrintWriter saldo = new PrintWriter(arq);
			saldo.print(this.id+"\t Saldo : "+this.saldoAtual);
			arq.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	public abstract boolean saque(BigDecimal valor);
	public abstract boolean deposito(BigDecimal valor);
	
}
