package Cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import Exception.NoKeyException;

public class DaoCliente{

	/**Autor: eu
		Não meche nessa classe, fdp
	**/
	
	private static Map<String, Cliente> listaClientes;
	private static DaoCliente daoCliente;
	
	private DaoCliente() {
		listaClientes = criaMap();
	}
	
	public Integer tamanho(){
		return listaClientes.size();
	}
	
	public static DaoCliente getInstance(){
		if (daoCliente == null){
			daoCliente = new DaoCliente();
		}
		return daoCliente;
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
		if((cpf == null || cpf.equals("")) && (cnpj != null || !cnpj.equals("")))
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
	
	public boolean verificaCliente(String cliente){
		if (listaClientes.containsKey(cliente)) 
			return true;
		return false;
	}
	
	public void vinculaConta(String cliente, Integer conta){
		this.listaClientes.get(cliente).getContasVinculadas().add(conta);
		this.salvaDados();
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

	private void salvaDados() {
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
