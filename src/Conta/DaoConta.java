package Conta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Conta.Registros.Registro;
import Exception.NoKeyException;

public class DaoConta{
	
	private static Map<Integer, Conta> listaContas;
	private static DaoConta daoConta;
	
	private DaoConta(){
		listaContas = getListaContas();
	}
	
	public static DaoConta getInstance(){
		if (daoConta == null){
			return new DaoConta();
		} return daoConta;
	}
	
	public Integer abrirConta(String senha, String cliente, BigDecimal saldoAtual, BigDecimal limite, BigDecimal juros){
		Integer numeroDaConta = geraNumeroConta();
		Conta conta = criaConta(numeroDaConta, senha, cliente, saldoAtual, limite, juros);
		if (conta == null)
			return -1;
		listaContas.put(conta.getId(), conta);
		salvaListaContas();
		return numeroDaConta;
	}
	
	public boolean excluiConta(Integer numeroConta, String senha) throws NoKeyException{
		if (this.listaContas.containsKey(senha) == false)
			throw new NoKeyException();
		Conta conta = this.listaContas.get(numeroConta);
		if (conta.getSenha().equals(senha) && conta.getSaldoAtual().doubleValue() <= 0.0){
			this.listaContas.remove(numeroConta);
			salvaListaContas();
			return true;
		}
		return false;
	}
	
	public boolean validaSenha(Integer numeroConta, String senha){
			Conta conta = this.listaContas.get(numeroConta);
			if (conta.getSenha().equals(senha))
				return true;
			return false;
	}
	
	public BigDecimal getSaldoConta(Integer numeroConta, String senha) throws NoKeyException{
		if (listaContas.containsKey(numeroConta) == false)
			throw new NoKeyException();
		
		Conta conta = this.listaContas.get(numeroConta);
		if (conta.getSenha().equals(senha))
			return conta.getSaldoAtual();
		return null;
	}
	
	public boolean subtraiSaldo(Integer numeroConta, BigDecimal valor){
		Conta conta = this.listaContas.get(numeroConta);
		BigDecimal saldo = conta.getSaldoAtual();
		if (saldo.compareTo(valor)<0)
			return false;
		conta.setSaldoAtual(saldo.subtract(valor));
		salvaListaContas();
		return true;
	}
	
	public boolean incrementaSaldo(Integer numeroConta, BigDecimal valor){
		Conta conta = this.listaContas.get(numeroConta);
		BigDecimal saldo = conta.getSaldoAtual();
		if (valor.compareTo(new BigDecimal("0.0"))<=0)
			return false;
		conta.setSaldoAtual(saldo.add(valor));
		salvaListaContas();
		return true;		
	}
	
	public Map<Integer, Conta> listaContas(){
		return listaContas;
	}
	
	public StringBuffer criaExtrato(Integer numeroConta, String senha, LocalDate ini, LocalDate fim){
		Conta conta = listaContas.get(numeroConta);
	
		
		StringBuffer extrato = new StringBuffer("\tExtrato da conta "+conta.getId()+"\n");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dtfh = DateTimeFormatter.ofPattern("hh:mm");
		extrato.append("\tPeriodo: "+dtf.format(ini)+" até "+dtf.format(fim)+"\n\n");
		
		LinkedList<Registro> transacoes= conta.getTransacoes();
		
		if (conta.getSenha().equals(senha) == false)
			return null;
		if (transacoes.size() == 0){
			extrato.append("Não houveram transações nessa conta\n\n");
			extrato.append("\n\n\t\t Saldo atual: "+conta.getSaldoAtual());
			return extrato;
		}
		
		for (Registro registro : transacoes) {
			if ((registro.getData().isAfter(ini) || registro.getData().isEqual(ini)) && (registro.getData().isBefore(fim) || registro.getData().isEqual(fim))){
				BigDecimal saque = registro.getSaque();
				BigDecimal deposito = registro.getDeposito();
				extrato.append("Data: "+ registro.getData()+ " "+dtfh.format(registro.getHora()));
				if (saque.doubleValue() > 0 && deposito.doubleValue() == 0 || deposito == null){
					extrato.append(" Saque de: "+ saque.floatValue()+"\n");
				}else if (saque.doubleValue() == 0 && deposito.doubleValue() > 0 || saque == null){
					extrato.append(" Deposito de: "+ deposito.floatValue()+"\n");
				}else{
					extrato.append("Operação não identificada\n");
				}
			}
		}
		extrato.append("\n\n\t\t Saldo atual: "+conta.getSaldoAtual());
		
		return extrato;
	}
	
	public void criaExtratoTxT(Integer numeroConta, String senha, LocalDate ini, LocalDate fim) throws IOException{
		Conta conta = listaContas.get(numeroConta);
		String nomeDoArquivo = conta.getId()+" "+LocalDate.now();
		PrintWriter pw = new PrintWriter( new BufferedWriter(new FileWriter(nomeDoArquivo+".doc")));
		StringBuffer extrato = this.criaExtrato(numeroConta, senha, ini, fim);
		String saida = extrato.toString();
		pw.write(saida);
		pw.close();
	}
	
	public void registraOperacao(Integer numeroConta, Registro operacao){
		Conta conta = listaContas.get(numeroConta);
		conta.getTransacoes().add(operacao);
		salvaListaContas();
	}
		
	private Conta criaConta(int id, String senha, String cliente, BigDecimal saldoAtual, BigDecimal limite, BigDecimal juros){
	
		if (limite.doubleValue() > 0 && juros.doubleValue() <= 0){
			return new ContaEspecial(id, senha, cliente, saldoAtual, limite);
		} else if (juros.doubleValue() > 0 && limite.doubleValue() <= 0){
			return new ContaPoupanca(id, senha, cliente, juros);
		} else if (limite.doubleValue() == 0 && juros.doubleValue() == 0){
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
