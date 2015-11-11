package Conta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Cliente.DaoCliente;

public class DaoConta{
	
	private static Map<Integer, Conta> listaContas;
	private DaoCliente daoCliente;
	
	public DaoConta(){
		listaContas = getListaContas();
		daoCliente = new DaoCliente();
	}
	
	public boolean abrirConta(String senha, String cliente, BigDecimal saldoAtual, BigDecimal limite, BigDecimal juros){
		Conta conta = criaConta(1, senha, cliente, saldoAtual, limite, juros);
		if (conta == null)
			return false;
		System.out.println(conta.getId());
		listaContas.put(conta.getId(), conta);
		daoCliente.vincularConta(cliente, conta);
		daoCliente.salvaDados();
		salvaListaContas();
		return true;
	}
	
	public Map<Integer, Conta> listaContas(){
		return listaContas;
	}
	
	public boolean encerrarConta(Integer id, String senha){
		Conta contaTemp = listaContas.get(id);
		if (contaTemp.getSaldoAtual().doubleValue() > 0){
			return false;
		} if (contaTemp.getSenha().equals(senha) ){
			contaTemp.setSituacao(false);
			contaTemp.setDataEncerramento(LocalDate.now().plusMonths(1));
			salvaListaContas();
			daoCliente.salvaDados();
			return true;
		}return false;
	}
	
	private Conta criaConta(int id, String senha, String cliente, BigDecimal saldoAtual, BigDecimal limite, BigDecimal juros){
	
		if (limite.intValue() > 0){
			return new ContaEspecial(id, senha, cliente, saldoAtual, limite);
		} else if (juros.doubleValue() > 0){
			return new ContaPoupanca(id, senha, cliente, saldoAtual, juros);
		} else if (limite.intValue() == 0 && juros.intValue() == 0){
			return new ContaCorrente(id, senha, cliente, saldoAtual);
		}return null;
	}
	
	private Integer geraNumeroConta(){
		int numero = (int) (Math.random()*100)+1;
		for (Integer integer : listaContas.keySet()) {
			if (numero == integer)
				return geraNumeroConta();
		}return numero;
	}
	
	private Map<Integer, Conta> getListaContas(){
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("listaContas.bin")));
			Map<Integer, Conta> temp = (Map<Integer, Conta>) ois.readObject();
			ois.close();
			return temp;
		} catch (IOException | ClassNotFoundException e) {
			return new HashMap<Integer, Conta>();
		}
	}
	
	private void salvaListaContas(){
		ObjectOutputStream oos;
		try{
			oos = new ObjectOutputStream(new FileOutputStream(new File("listaContas.bin")));
			oos.writeObject(listaContas);
			oos.close();
		}catch(IOException e){
			
		}
	}
	
}
