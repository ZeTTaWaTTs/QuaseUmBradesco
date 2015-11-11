import java.io.IOException;

import Cliente.Cliente;
import Cliente.DaoCliente;
import Cliente.NoKeyException;
import Conta.Conta;
import Conta.DaoConta;

public class TestPersistencia {
	public static void main(String[] args) throws IOException, NoKeyException {
		
		DaoCliente daoCliente = new DaoCliente();
		DaoConta daoConta = new DaoConta();
		
		//daoCliente.cadastrarCliente("Baka", "endereco", "cep", "telefone", new BigDecimal("1000"), "cpf", "", "rg");
		//daoConta.abrirConta("senha", "cpf", new BigDecimal("1000"), new BigDecimal("0.0"), new BigDecimal("0.0"));
		
		//Conta conta = daoConta.listaContas().get(1);
		Cliente cliente = daoCliente.consultarCliente("cpf");
				
		//conta.setSaldoAtual(new BigDecimal("324234"));
		//conta.setSaldoAtual(new BigDecimal ("2000"));
		
		System.out.println(cliente.getContasVinculadas().size());
		
	}
}
