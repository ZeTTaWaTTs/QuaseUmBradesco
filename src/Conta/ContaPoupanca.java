package Conta;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{

	private BigDecimal juros;
	
	public ContaPoupanca(int id, String senha, String cliente, BigDecimal saldoAtual, BigDecimal juros) {
		super(id, senha, cliente, saldoAtual);
		this.juros = juros;
	}

}
