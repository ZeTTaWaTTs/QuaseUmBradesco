package Conta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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

import Conta.Registros.ListaMovimentos;
import Conta.Registros.Movimento;
import Conta.Registros.RegistraMovimento;
import Exception.NoKeyException;

public class DaoConta {

	private Map<Integer, Conta> listaContas;
	private static DaoConta daoConta;

	private DaoConta() {
		listaContas = getListaContas();
	}

	public static DaoConta getInstance() {
		if (daoConta == null) {
			return new DaoConta();
		}
		return daoConta;
	}

	public Integer abrirConta(String senha, String cliente,
			BigDecimal saldoAtual, BigDecimal limite, BigDecimal juros) {
		Integer numeroDaConta = geraNumeroConta();
		Conta conta = criaConta(numeroDaConta, senha, cliente, saldoAtual,
				limite, juros);
		if (conta == null)
			return -1;
		listaContas.put(conta.getId(), conta);
		salvaListaContas();
		return numeroDaConta;
	}

	public boolean excluiConta(Integer numeroConta, String senha)
			throws NoKeyException {
		if (this.listaContas.containsKey(numeroConta) == false)
			throw new NoKeyException();
		Conta conta = this.listaContas.get(numeroConta);
		if (validaSenha(numeroConta, senha)
				&& conta.getSaldoAtual().doubleValue() <= 0.0) {
			this.listaContas.remove(numeroConta);
			salvaListaContas();
			return true;
		}
		return false;
	}

	public boolean validaSenha(Integer numeroConta, String senha) {
		Conta conta = this.listaContas.get(numeroConta);
		if (conta.getSenha().equals(senha))
			return true;
		return false;
	}

	public BigDecimal getSaldoConta(Integer numeroConta, String senha)
			throws NoKeyException {
		if (listaContas.containsKey(numeroConta) == false)
			throw new NoKeyException();

		if (validaSenha(numeroConta, senha)) {
			Conta conta = this.listaContas.get(numeroConta);

			return conta.getSaldoAtual();
		}
		return null;
	}

	public void rendimento(BigDecimal juros) {// !!!!!!!!!!!!!!!!talvez lance uma exce��o ConcurrentModificationException!!!!!!!!!!!!!!!

		for (Integer chave : listaContas.keySet()) {
			if (listaContas.get(chave) instanceof ContaPoupanca) {

				((ContaPoupanca) listaContas.get(chave))
						.calculaRendimento(juros);
			}
		}

	}

	public boolean deposito(BigDecimal valor, String senha, Integer numeroConta) {
		Conta conta = listaContas.get(numeroConta);
		if (validaSenha(numeroConta, senha)) {
			if (conta instanceof ContaCorrente) {
				((ContaCorrente) conta).deposito(valor);
				salvaSaldoContaTxt(numeroConta);
				return true;
				
			} else if (conta instanceof ContaEspecial) {
				((ContaEspecial) conta).deposito(valor);
				salvaSaldoContaTxt(numeroConta);
				return true;
			} else {
				((ContaPoupanca) conta).deposito(valor);
				salvaSaldoContaTxt(numeroConta);
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean saque(BigDecimal valor, String senha, Integer numeroConta) {
		Conta conta = listaContas.get(numeroConta);
		if (validaSenha(numeroConta, senha)) {
			if (conta instanceof ContaCorrente) {
				return ((ContaCorrente) conta).saque(valor);
			} else if (conta instanceof ContaEspecial) {
				return ((ContaEspecial) conta).saque(valor);
			} else {
				return ((ContaPoupanca) conta).saque(valor);
			}
		} else {
			return false;
		}
	}

	public Map<Integer, Conta> listaContas() {
		return listaContas;
	}

	public StringBuffer criaExtrato(Integer numeroConta, String senha,
			LocalDate ini, LocalDate fim) {
		Conta conta = listaContas.get(numeroConta);

		StringBuffer extrato = new StringBuffer("\tExtrato da conta "
				+ conta.getId() + "\n");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dtfh = DateTimeFormatter.ofPattern("hh:mm");
		extrato.append("\tPeriodo: " + dtf.format(ini) + " at� "
				+ dtf.format(fim) + "\n\n");

		ListaMovimentos<RegistraMovimento> transacoes = conta.getTransacoes();

		if (conta.getSenha().equals(senha) == false)
			return null;
		if (transacoes.size() == 0) {
			extrato.append("N�o houveram transa��es nessa conta\n\n");
			extrato.append("\n\n\t\t Saldo atual: " + conta.getSaldoAtual());
			return extrato;
		}

		for (Movimento registro : transacoes) {
			if ((registro.getData().isAfter(ini) || registro.getData().isEqual(
					ini))
					&& (registro.getData().isBefore(fim) || registro.getData()
							.isEqual(fim))) {
				BigDecimal saque = registro.getSaque();
				BigDecimal deposito = registro.getDeposito();
				extrato.append("Data: " + registro.getData() + " "
						+ dtfh.format(registro.getHora()));
				if (saque.doubleValue() > 0 && deposito.doubleValue() == 0
						|| deposito == null) {
					extrato.append(" Saque de: " + saque.floatValue() + "\n");
				} else if (saque.doubleValue() == 0
						&& deposito.doubleValue() > 0 || saque == null) {
					extrato.append(" Deposito de: " + deposito.floatValue()
							+ "\n");
				} else {
					extrato.append("Opera��o n�o identificada\n");
				}
			}
		}
		extrato.append("\n\n\t\t Saldo atual: " + conta.getSaldoAtual());

		return extrato;
	}

	public void criaExtratoTxT(Integer numeroConta, String senha,
			LocalDate ini, LocalDate fim) throws IOException {
		Conta conta = listaContas.get(numeroConta);
		String nomeDoArquivo = conta.getId() + " " + LocalDate.now();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				nomeDoArquivo + ".doc")));
		StringBuffer extrato = this.criaExtrato(numeroConta, senha, ini, fim);
		String saida = extrato.toString();
		pw.write(saida);
		pw.close();
	}

	public void registraOperacao(Integer numeroConta, Registro operacao) {
		Conta conta = listaContas.get(numeroConta);
		conta.getTransacoes().add(operacao);
		salvaListaContas();
	}

	private Conta criaConta(int id, String senha, String cliente,
			BigDecimal saldoAtual, BigDecimal limite, BigDecimal juros) {

		if (limite.doubleValue() > 0 && juros.doubleValue() <= 0) {
			return new ContaEspecial(id, senha, cliente, saldoAtual, limite);
		} else if (juros.doubleValue() > 0 && limite.doubleValue() <= 0) {
			return new ContaPoupanca(id, senha, cliente);
		} else if (limite.doubleValue() == 0 && juros.doubleValue() == 0) {
			return new ContaCorrente(id, senha, cliente, saldoAtual);
		}
		return null;
	}

	private Integer geraNumeroConta() {
		int numero = (int) (Math.random() * 100) + 1;
		for (Integer integer : listaContas.keySet()) {
			if (numero == integer)
				return geraNumeroConta();
		}
		return numero;
	}

	private Map<Integer, Conta> getListaContas() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(
					"listaContas.bin")));
			Map<Integer, Conta> temp = (Map<Integer, Conta>) ois.readObject();
			ois.close();
			return temp;
		} catch (IOException | ClassNotFoundException e) {
			return new HashMap<Integer, Conta>();
		}
	}

	private void salvaListaContas() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(
					"listaContas.bin")));
			oos.writeObject(listaContas);
			oos.close();
		} catch (IOException e) {

		}
	}

	public void salvaSaldoContaTxt(Integer numeroConta) {
		try {
			Conta conta = this.listaContas.get(numeroConta);
			FileWriter arq = new FileWriter(conta.getCliente() + "." + numeroConta + "."
					+ ".txt");
			PrintWriter grava = new PrintWriter(arq);

			grava.println("Conta: " + numeroConta);
			grava.println("Cliente" + conta.getCliente());
			if(conta instanceof ContaPoupanca){
				grava.println("Saldo: " + ((ContaPoupanca) conta).saldoAtualP());
			}else{
			grava.println("Saldo: " + conta.getSaldoAtual());
			}
			arq.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Arquivo n�o encontrado: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Erro na abertura do arquivo:" + "$#�&*%."+numeroConta + e.getMessage());
		}
	}

	public void getSaldoContaTxt(Integer numeroConta) {
		Conta conta = this.listaContas.get(numeroConta);
		FileReader arq;
		try {
			arq = new FileReader(conta.getCliente()+"."+numeroConta);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();

			while (linha != null) {
				System.out.printf(linha);
				linha = lerArq.readLine();
			}

			lerArq.close();

		} catch (FileNotFoundException e) {
			System.err.println("Erro: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}
}
