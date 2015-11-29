package Conta;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPoupanca extends Conta {

	private BigDecimal rendimento;
	private BigDecimal saldo[];

	public ContaPoupanca(int id, String senha, String cliente,
			BigDecimal rendimento) {
		super(id, senha, cliente);
		this.rendimento = rendimento;
		this.saldo = inicializaVetor();
	}

	public BigDecimal getRendimento() {
		return rendimento;
	}

	public void setRendimento(BigDecimal rendimento) {
		this.rendimento = this.rendimento.add(rendimento);
	}

	public BigDecimal[] getSaldo() {
		return saldo;
	}

	public void addSaldo(BigDecimal valor, int dia) {
		this.saldo[dia] = this.saldo[dia].add(valor);
	}

	public void removeSaldo(BigDecimal valor) {
		BigDecimal aux = null;

		for (int i = 0; i < this.saldo.length; i++) {
			if (aux == null) {
				this.saldo[i] = this.saldo[i].subtract(valor);
				aux = this.saldo[i];
			} else if (aux.compareTo(new BigDecimal("0.0")) == -1) {
				this.saldo[i] = aux.add(this.saldo[i]);
			}
			if (this.saldo[i].compareTo(new BigDecimal("0.0")) == -1) {
				this.saldo[i] = new BigDecimal("0.0");
			}

		}
	}

	public BigDecimal saldoAtualP() {// analisa aqui porra

		BigDecimal soma = new BigDecimal("0");

		for (int i = 0; i < this.saldo.length; i++) {
			soma = this.saldo[i].add(soma);

		}
		return soma;
	}

	public BigDecimal[] inicializaVetor() {
		BigDecimal[] vetor = new BigDecimal[28];
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = new BigDecimal("0");
		}
		return vetor;
	}

	public BigDecimal calculaRendimento(BigDecimal juros) {
		BigDecimal rendimento = null;
		LocalDate hj = LocalDate.now();

		rendimento = this.saldo[hj.getDayOfMonth() - 1].multiply(juros);
		setRendimento(rendimento);
		return rendimento;
	}
	
	@Override
	public boolean deposito(BigDecimal valor) {
		LocalDate hj = LocalDate.now();
		if (valor.compareTo(new BigDecimal("0.1")) == -1) {
			return false;
		} else if (hj.getDayOfMonth() > 28) {
			addSaldo(valor, 0);
			return true;
		} else {
			addSaldo(valor, hj.getDayOfMonth() - 1);
			return true;
		}
	}
	
	@Override
	public boolean saque(BigDecimal valor) {
		if (valor.compareTo(new BigDecimal("0.1")) == -1) {
			return false;
		} else if (valor.compareTo(saldoAtualP()) == 1) {
			return false;
		} else {
			removeSaldo(valor);
			return true;
		}
	}

}
