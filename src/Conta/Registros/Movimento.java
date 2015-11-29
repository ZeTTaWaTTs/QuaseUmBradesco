package Conta.Registros;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Movimento {
	BigDecimal valor, juros;
	String operacao;
	LocalTime hora;
	LocalDate data;

	public Movimento(BigDecimal valor, String operacao, LocalTime hora,
			LocalDate data) {

		this.valor = valor;
		this.operacao = operacao;

		this.hora = hora;
		this.data = data;

	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getJuros() {
		return juros;
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
