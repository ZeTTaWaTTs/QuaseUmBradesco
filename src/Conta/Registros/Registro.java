package Conta.Registros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import Exception.InvalidOperationException;

public class Registro implements Serializable{

	private BigDecimal saque, juros, deposito;
	private LocalDate data;
	private LocalTime hora;
	
	private Registro(BigDecimal saque, BigDecimal deposito,  BigDecimal juros) throws InvalidOperationException{
		if (saque.doubleValue() > 0 && deposito.intValue() == 0 || deposito == null){
			this.saque = saque;
			this.deposito = new BigDecimal("0.0");
		}else if(saque.intValue() == 0 && deposito.doubleValue() > 0 || saque == null){
			this.saque = new BigDecimal("0.0");
			this.deposito = deposito;
		}else{
			throw new InvalidOperationException();
		}
		this.juros = juros;
		this.data = LocalDate.now();
		this.hora = LocalTime.now();
	}
	
	public static Registro registraSaque(BigDecimal saque, BigDecimal juros) throws InvalidOperationException{
		return new Registro(saque, new BigDecimal("0.0"), juros);
	}
	
	public static Registro registraDeposito(BigDecimal deposito,  BigDecimal juros) throws InvalidOperationException{
		return new Registro(new BigDecimal("0.0"), deposito, juros);
	}
	
	public BigDecimal getSaque() {
		return saque;
	}
	public BigDecimal getJuros() {
		return juros;
	}
	public BigDecimal getDeposito() {
		return deposito;
	}
	public LocalDate getData() {
		return data;
	}
	public LocalTime getHora() {
		return hora;
	}
	
	
	
}
