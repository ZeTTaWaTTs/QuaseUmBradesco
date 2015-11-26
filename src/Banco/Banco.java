package Banco;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import Cliente.DaoCliente;
import Conta.Conta;
import Conta.DaoConta;
import Conta.Registros.Registro;
import Exception.InvalidOperationException;
import Exception.NoKeyException;

public class Banco {

	private DaoCliente daoCliente;
	private DaoConta daoConta;
	
	public Banco(){
		this.daoCliente = DaoCliente.getInstance();
		this.daoConta = DaoConta.getInstance();
	}
	
	public boolean abrirConta (String senha, String cliente, BigDecimal saldoAtual, BigDecimal limite, BigDecimal juros){
		Integer numeroConta = daoConta.abrirConta(senha, cliente, saldoAtual, limite, juros);
		if (!daoCliente.verificaCliente(cliente))
			return false;
		if (numeroConta == -1)
			return false;
		daoCliente.vinculaConta(cliente, numeroConta);
		return true;
	}
	
	public boolean excluirConta (Integer numeroConta, String senha) throws NoKeyException, IOException{
		Conta conta = daoConta.listaContas().get(numeroConta);
		String cliente = conta.getCliente();
		if (daoCliente.verificaCliente(cliente) == false)
			return false;
		List<Integer> lista = daoCliente.consultarCliente(cliente).getContasVinculadas();
		for (int i = 0; i<lista.size() ; i++){
			if (lista.get(i) == numeroConta)
				lista.remove(i);
		}
		return true;
	}
	
	public boolean saqueConta(Integer numeroConta, String senha, BigDecimal valor) throws InvalidOperationException{
		if (daoConta.validaSenha(numeroConta, senha) ==  false)
			return false;
		if (daoConta.subtraiSaldo(numeroConta, valor)){
			Registro operacao = Registro.registraSaque(valor, new BigDecimal("0.0"));
			daoConta.registraOperacao(numeroConta, operacao);
			return true;
		}return false;
	}
	
	public boolean depositoConta(Integer numeroConta, String senha, BigDecimal valor) throws InvalidOperationException {
		if (daoConta.validaSenha(numeroConta, senha) ==  false)
			return false;
		if ( daoConta.incrementaSaldo(numeroConta, valor)){
			Registro operacao = Registro.registraDeposito(valor, new BigDecimal("0.0"));
			daoConta.registraOperacao(numeroConta, operacao);
			return true;
		}return false;
	}
	
	
}