package Conta.Registros;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Movimento {
	int conta;
	BigDecimal valor;
	String operacao;
	private LocalDate data;
	private LocalTime hora;

	public Movimento(int conta, BigDecimal valor, String operacao
			) {

		this.valor = valor;
		this.operacao = operacao;
		this.hora = LocalTime.now();
		this.data = LocalDate.now();
	}

	public BigDecimal getValor() {
		return valor;
	}
	public int  getConta() {
		return conta;
	}

	public String getOperacao() {
		return operacao;
	}

	public LocalTime getHora() {
		return hora;
	}

	public LocalDate getData() {
		return data;
	}
}
