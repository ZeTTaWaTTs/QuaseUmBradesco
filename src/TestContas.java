import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import Cliente.Cliente;
import Cliente.DaoCliente;
import Cliente.NoKeyException;
import Conta.Conta;
import Conta.DaoConta;

public class TestContas {

	DaoCliente daoCliente = new DaoCliente();
	DaoConta daoConta = new DaoConta();
	
	@Test
	public void testAbrirConta() throws IOException, NoKeyException {
		daoCliente.cadastrarCliente("Baka", "endereco", "cep", "telefone", new BigDecimal("1000"), "cpf", "", "rg");
		daoConta.abrirConta("senha", "cpf", new BigDecimal("1000"), new BigDecimal("0.0"), new BigDecimal("0.0"));
		Conta conta = daoConta.listaContas().get(1);
		Cliente cliente = daoCliente.consultarCliente("cpf");
		conta.setSaldoAtual(new BigDecimal("324234"));
		assertEquals(conta.getSaldoAtual(), cliente.getContasVinculadas().get(0).getSaldoAtual());
	}
	
	public void testEncerramento(){
		
	}

}
