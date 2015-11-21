package Conta;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{

	private BigDecimal juros;
	private BigDecimal[] saldoAtual;
	
	public ContaPoupanca(int id, String senha, String cliente, BigDecimal juros) {
		super(id, senha, cliente);
		this.juros = juros;
		this.saldoAtual = this.vetorSaldo();
	}

	private BigDecimal[] vetorSaldo(){
		BigDecimal[] temp = new BigDecimal[28];
		for (int i = 0; i < 28 ; i++){
			temp[i] = new BigDecimal("0.0");
		}return temp;
	}
}
