package Conta;

import java.math.BigDecimal;

public class ContaEspecial extends Conta{

	private BigDecimal limite;
	
	public ContaEspecial(int id, String senha, String cliente, BigDecimal saldoAtual, BigDecimal limite) {
		super(id, senha, cliente,saldoAtual);
		this.limite = limite;
		setSaldoAtual(getSaldoAtual().add(limite));
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	@Override
	public boolean saque(BigDecimal valor) {
		if(valor.compareTo(new BigDecimal("0"))<0){
			return false;
		}
		else if(valor.compareTo(getSaldoAtual())>0){
			return false;
		}
		else{
			setSaldoAtual(getSaldoAtual().subtract(valor));
			return true;
		}
	}

	@Override
	public boolean deposito(BigDecimal valor) {
		if(valor.compareTo(new BigDecimal("0"))<0){
			return false;
		}
		else if(valor.compareTo(getSaldoAtual())>0){
			setSaldoAtual(getSaldoAtual().add(valor));
			return true;
		}
		
		setSaldoAtual(getSaldoAtual().add(valor));
		return true;
		
	}

	
	
}
