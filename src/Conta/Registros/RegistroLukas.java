package Conta.Registros;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

public class RegistroLukas {

	private LocalDate data;
	private LocalTime hora;

	public Object registraMovimentacaoContaCorrente(long conta,
			BigDecimal saque, BigDecimal deposito) {
		// VERIFICAÇÃO DE MOVIMENTO DE CONTA NO QUESITO DEPOSITO
		if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) {
			String operacao = "Movimento de Deposito na Conta Corrente";
			BigDecimal valor = deposito;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, conta, hora, data);

			return mov;

			// VERIFICAÇÃO DE MOVIMENTO DE CONTA NO QUESITO SAQUE

		} else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
			String operacao = "Movimento de Saque na Conta Corrente";
			BigDecimal valor = saque;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, conta, hora, data);

			return mov;

		} else {
			JOptionPane.showMessageDialog(null,
					"Não pode de uma operação ao mesmo tempo.");
			return null;

		}

	}

	public Object registraMovimentacaoContaPoupanca(long conta,
			BigDecimal saque, BigDecimal deposito) {
		// VERIFICAÇÃO DE MOVIMENTO DE CONTA NO QUESITO DEPOSITO
		if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) {
			String operacao = "Movimento de Deposito na Conta Poupanca";
			BigDecimal valor = deposito;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, conta, hora, data);

			return mov;

			// VERIFICAÇÃO DE MOVIMENTO DE CONTA NO QUESITO SAQUE

		} else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
			String operacao = "Movimento de Saque na Conta Poupanca";
			BigDecimal valor = saque;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, conta, hora, data);

			return mov;

		} else {
			JOptionPane.showMessageDialog(null,
					"Não pode de uma operação ao mesmo tempo.");
			return null;

		}

	}

	public Object registraMovimentacaoContaEspecial(long conta,
			BigDecimal saque, BigDecimal deposito) {
		// VERIFICAÇÃO DE MOVIMENTO DE CONTA NO QUESITO DEPOSITO
		if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) {
			String operacao = "Movimento de Deposito na Conta Especial";
			BigDecimal valor = deposito;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, conta, hora, data);

			return mov;

			// VERIFICAÇÃO DE MOVIMENTO DE CONTA NO QUESITO SAQUE

		} else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
			String operacao = "Movimento de Saque na Conta Especial";
			BigDecimal valor = saque;
			this.hora = LocalTime.now();
			this.data = LocalDate.now();
			Movimento mov = new Movimento(valor, operacao, conta, hora, data);

			return mov;

		} else {
			JOptionPane.showMessageDialog(null,
					"Não pode de uma operação ao mesmo tempo.");
			return null;

		}
	}

	public Object juros(long conta, BigDecimal juros) {
		String operacao = "Movimento de Juros";
		BigDecimal valor = juros;
		this.hora = LocalTime.now();
		this.data = LocalDate.now();

		Movimento mov = new Movimento(valor, operacao, conta, hora, data);
		return mov;
	}

}