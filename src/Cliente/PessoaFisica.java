package Cliente;

import java.math.BigDecimal;

public class PessoaFisica extends Cliente{

	private String Cpf,Rg;

	public PessoaFisica(String nome, String endereco, String cep, String telefone, BigDecimal renda, String cpf,
	String rg) {
		super(nome, endereco, cep, telefone, renda);
		this.Cpf = cpf;
		this.Rg = rg;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getRg() {
		return Rg;
	}

	public void setRg(String rg) {
		Rg = rg;
	}
}