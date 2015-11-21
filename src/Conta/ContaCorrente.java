package Conta;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{

	public ContaCorrente(int id, String senha, String cliente, BigDecimal saldoAtual) {
		super(id, senha, cliente, saldoAtual);
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
		else{
			setSaldoAtual(getSaldoAtual().add(valor));
			return true;
		}
		
	}

}
