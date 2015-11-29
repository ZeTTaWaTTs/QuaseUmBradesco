package Conta.Registros;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

public class RegistraMovimento {

	private LocalDate data;
	private LocalTime hora;

	public Movimento registraMovimentacaoContaCorrente(
			BigDecimal saque, BigDecimal deposito) {

		if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) {
			String operacao = "Movimento de Deposito na Conta Corrente";
			BigDecimal valor = deposito;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, hora, data);

			return mov;

		} else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
			String operacao = "Movimento de Saque na Conta Corrente";
			BigDecimal valor = saque;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, hora, data);

			return mov;

		} else {
			JOptionPane.showMessageDialog(null, "Operação invalida !.");
			return null;

		}

	}

	public Movimento registraMovimentacaoContaPoupanca(
			BigDecimal saque, BigDecimal deposito) {

		if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) {
			String operacao = "Movimento de Deposito na Conta Poupanca";
			BigDecimal valor = deposito;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, hora, data);

			return mov;

		} else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
			String operacao = "Movimento de Saque na Conta Poupanca";
			BigDecimal valor = saque;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, hora, data);

			return mov;

		} else {
			JOptionPane.showMessageDialog(null, "Operação invalida.");
			return null;

		}

	}

	public Movimento registraMovimentacaoContaEspecial(
			BigDecimal saque, BigDecimal deposito) {
		
		if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) {
			String operacao = "Movimento de Deposito na Conta Especial";
			BigDecimal valor = deposito;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, hora, data);

			return mov;

			

		} else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
			String operacao = "Movimento de Saque na Conta Especial";
			BigDecimal valor = saque;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, hora, data);

			return mov;

		} else {
			JOptionPane.showMessageDialog(null,
					"Operação invalida !");
			return null;

		}
	}

	public Movimento juros( BigDecimal juros) {
		String operacao = "Movimento de Juros";
		BigDecimal valor = juros;
		this.hora = LocalTime.now();
		this.data = LocalDate.now();

		Movimento mov = new Movimento(valor, operacao, hora, data);
		return mov;
	}

}