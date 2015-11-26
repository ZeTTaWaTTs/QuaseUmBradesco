package Conta.Registros;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Movimento {
	BigDecimal valor, jurosConstrutor;
	String operacao;
	long conta;
	LocalTime hora;
	LocalDate data;

	public Movimento(BigDecimal valor, String operacao, long conta,
			LocalTime hora, LocalDate data) {

		this.valor = valor;
		this.operacao = operacao;
		this.conta = conta;
		this.hora = hora;
		this.data = data;

	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getOperacao() {
		return operacao;
	}

	public long getConta() {
		return conta;
	}

	public LocalTime getHora() {
		return hora;
	}

	public LocalDate getData() {
		return data;
	}
}
