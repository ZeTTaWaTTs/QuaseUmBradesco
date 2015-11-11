package Cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Conta.Conta;

public class DaoCliente{

	/**Autor: eu
		Não meche nessa classe, fdp
	**/
	
	private static Map<String, Cliente> listaClientes;

	public DaoCliente() {
		listaClientes = criaMap();
	}

	public Cliente clienteFisicoMenorSaldo(){
		Cliente temp=null;
		BigDecimal menorSaldo = null;
		for (String key : listaClientes.keySet()) {
			BigDecimal saldoConta = new BigDecimal("0.0");
			if (listaClientes.get(key) instanceof PessoaFisica){
				for (Conta conta : listaClientes.get(key).getContasVinculadas()) {
					saldoConta=saldoConta.add(conta.getSaldoAtual());
				}
				if(menorSaldo == null){
					menorSaldo = saldoConta;
				    temp = (PessoaFisica) listaClientes.get(key);
				}
				if (saldoConta.compareTo(menorSaldo) == -1){
					menorSaldo=saldoConta;
					temp =  (PessoaFisica) listaClientes.get(key);
				}
			}
			
		}
		return temp;
	}
	
	public Cliente clienteJuridicoMenorSaldo(){
		Cliente temp=null;
		BigDecimal menorSaldo = null;
		for (String key : listaClientes.keySet()) {
			BigDecimal saldoConta = new BigDecimal("0.0");
			if (listaClientes.get(key) instanceof PessoaJuridica){
				for (Conta conta : listaClientes.get(key).getContasVinculadas()) {
					saldoConta = saldoConta.add(conta.getSaldoAtual());
				}
				if (menorSaldo == null) {
					menorSaldo = saldoConta;
					temp = (PessoaJuridica) listaClientes.get(key);
				}	
				if (saldoConta.compareTo(menorSaldo)==-1){
					menorSaldo = saldoConta;
					temp = (PessoaJuridica) listaClientes.get(key);
				}
			}
			
		}return temp;
	}
	
	public Cliente clienteJuridicoMaiorSaldo(){
		Cliente temp=null;
		BigDecimal maiorSaldo = null;
		for (String key : listaClientes.keySet()) {
			BigDecimal saldoConta = new BigDecimal("0.0");
			if (listaClientes.get(key) instanceof PessoaJuridica){
				for (Conta conta : listaClientes.get(key).getContasVinculadas()) {
					saldoConta = saldoConta.add(conta.getSaldoAtual());
				}
				if (maiorSaldo == null){
					maiorSaldo = saldoConta;
				 	temp = (PessoaJuridica) listaClientes.get(key);
				}	
				if (saldoConta.compareTo(maiorSaldo)==1){
					maiorSaldo = saldoConta;
					temp = (PessoaJuridica) listaClientes.get(key);
				}
			}
			
		}return temp;
	}
	
	public Cliente clienteFisicoMaiorSaldo(){
		Cliente temp=null;
		BigDecimal maiorSaldo = null;
		for (String key : listaClientes.keySet()) {
			BigDecimal saldoConta = new BigDecimal("0.0");
			if (listaClientes.get(key) instanceof PessoaFisica){
				for (Conta conta : listaClientes.get(key).getContasVinculadas()) {
					saldoConta = saldoConta.add(conta.getSaldoAtual());
				}
				if (maiorSaldo == null){
					maiorSaldo = saldoConta;
					temp = (PessoaFisica) listaClientes.get(key);
				}
				if (saldoConta.compareTo(maiorSaldo) == 1){
					maiorSaldo = saldoConta;
					temp = (PessoaFisica) listaClientes.get(key);
				}
			}
			
		}return temp;
	}
	//Metodo pra cadastrar novo cliente
	public boolean cadastrarCliente(String nome, String endereco, String cep, String telefone,BigDecimal renda, String cpf, String cnpj, String rg) throws IOException, NoKeyException {
		Cliente cliente = criaCliente(nome, endereco, cep, telefone, renda, cpf, cnpj, rg);
		if (cliente instanceof PessoaFisica) {
			listaClientes.put(((PessoaFisica) cliente).getCpf(), cliente);
			salvaDados();
			return true;
		} else if (cliente instanceof PessoaJuridica) {
			listaClientes.put(((PessoaJuridica) cliente).getCnpj(), cliente);
			salvaDados();
			return true;
		}return false;
	}

	//Metodo para modificar as contas de algum cliente
	public List<Conta> getContasCliente(String key){
		return listaClientes.get(key).getContasVinculadas();
	}
	
	//Metodo pra visualizar cliente
	public Cliente consultarCliente(String string) {
		if (!listaClientes.containsKey(string)) {
			return null;
		}
		return listaClientes.get(string);
	}

	//...
	public boolean alterarCliente(String nome, String endereco, String cep, String telefone,BigDecimal renda, String cpf, String cnpj, String rg) throws IOException, NoKeyException {
		String key;
		if((cpf == null || cpf.equals(""))&& (cnpj != null || !cnpj.equals("")))
			key = cnpj;
		else if((cnpj == null || cnpj.equals(""))&& (cpf != null || !cpf.equals("")))
			key = cpf;
		else
			throw new NoKeyException();
		if (!listaClientes.containsKey(key)) {
			return false;
		}
		listaClientes.put(key, criaCliente(nome, endereco, cep, telefone, renda, cpf, cnpj, rg));
		salvaDados();
		return true;
	}

	public boolean excluirCliente(String string) throws IOException, NoKeyException {
		if (!listaClientes.containsKey(string)) {
			throw new NoKeyException();
		} else if (!listaClientes.get(string).isSituacao()) {
			listaClientes.remove(string);
			salvaDados();
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean vincularConta(String cliente, Conta conta){
		return this.listaClientes.get(cliente).getContasVinculadas().add(conta);
	}

	//Não meche em nada daqui pra baixo tambem
	private Map<String, Cliente> criaMap(){
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Clientes.cliente")));
			Map<String, Cliente> mapa = (Map<String, Cliente>) ois.readObject();
			ois.close();
			return mapa;
		} catch (IOException | ClassNotFoundException e) {
			return new HashMap<>();
		}
	}

	public void salvaDados() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("Clientes.cliente")));
			oos.writeObject(listaClientes);
			oos.close();
		} catch (IOException e) {
		}
	}
	
	private Cliente criaCliente(String nome, String endereco, String cep, String telefone,BigDecimal renda, String cpf, String cnpj, String rg) throws NoKeyException{
		if((rg == null || rg.equals(""))&& (cpf == null || cpf.equals("")) & cnpj != null){
			return new PessoaJuridica(nome, endereco, cep, telefone, renda, cnpj);
		}else if((cnpj == null || cnpj.equals(""))&& cpf != null && rg != null){
			return new PessoaFisica(nome, endereco, cep, telefone, renda, cpf, rg);
		}throw new NoKeyException();
	}
}
