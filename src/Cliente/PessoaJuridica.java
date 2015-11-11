package Cliente;

import java.math.BigDecimal;

public class PessoaJuridica extends Cliente{

	private String Cnpj;

	public PessoaJuridica(String nome, String endereco, String cep, String telefone, BigDecimal renda, String cnpj) {
		super(nome, endereco, cep, telefone, renda);
		Cnpj = cnpj;
	}

	public String getCnpj() {
		return Cnpj;
	}

	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
	}	
}