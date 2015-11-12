package Cliente;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Conta.Conta;


public abstract class Cliente implements Serializable{
//tewste
	private String nome,endereco,Cep,telefone;
	private boolean situacao;
	private BigDecimal renda;
	private List<Conta> contasVinculadas;
	
	public Cliente(String nome, String endereco, String cep, String telefone,BigDecimal renda) {
		this.nome = nome;
		this.endereco = endereco;
		this.Cep = cep;
		this.telefone = telefone;
		this.situacao = false;
		this.renda = renda;
		this.contasVinculadas=new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return Cep;
	}
	public void setCep(String cep) {
		Cep = cep;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public boolean isSituacao() {
		return situacao;
	}
	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	public BigDecimal getRenda() {
		return renda;
	}
	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public List<Conta> getContasVinculadas() {
		return this.contasVinculadas;
	}
		
	
	
}
