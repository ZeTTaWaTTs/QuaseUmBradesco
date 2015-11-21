package Conta;

import java.math.BigDecimal;

public class ContaEspecial extends Conta{

	private BigDecimal limite;
	
	public ContaEspecial(int id, String senha, String cliente, BigDecimal saldoAtual, BigDecimal limite) {
		super(id, senha, cliente,saldoAtual);
		this.limite = limite;
		this.setSaldoAtual(this.getSaldoAtual().add(limite));
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	
	
}
