package Conta.Registros;

import java.math.BigDecimal;


import javax.swing.JOptionPane;

public class RegistraMovimento {

	ListaMovimentos<Movimento> lista = new ListaMovimentos<Movimento>();

	/*
	 * REGISTRO DE MOVIMENTO DAS CONTAS, RECEBE CONTA-SAQUE-DEPOISITO --- O
	 * METODO FAZ A COMPARAÇÃO PARA VER SE O VALORES DE ENTRADA SÃO > QUE 0 NOTA
	 * --- O PRIMEIRO IF, VERIFICA SE O SAQUE É < 0 E O DEPOSITO > 0, SENDO
	 * ASSIM DA PRA SUBTENDER QUE A OPERAÇÃO REQUISITADA É O DEPOISITO, E O
	 * MESMO ACONTECE COM O ELSE IF, SÓ QUE AO CONTRARIO CASO, SAQUE > 0 E
	 * DEPOSITO > 0, VAI GERAR UM JOPTIONPANE, PORQUE NA DA PRA SACAR E
	 * DEPOISITAR AO MESMO TEMPO .... TODOS OS METODOS ABAIXOS RETORNAM UM
	 * OBJETO PARA A LISTA DE MOVIMENTOS ....
	 */

	public void registraMovimentacaoContaCorrente(int conta, BigDecimal saque,
			BigDecimal deposito) {

		if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) {
			String operacao = "Movimento de Deposito na Conta Corrente";
			BigDecimal valor = deposito;

			Movimento mov = new Movimento(conta, valor, operacao);

			lista.addInFirst(mov);

		} else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
			String operacao = "Movimento de Saque na Conta Corrente";
			BigDecimal valor = saque;
			Movimento mov = new Movimento(conta, valor, operacao);

			lista.addInLast(mov);

		} else {
			JOptionPane.showMessageDialog(null, "Operação invalida !.");

		}

	}

	/*
	 * public Movimento registraMovimentacaoContaPoupanca(int conta, BigDecimal
	 * saque, BigDecimal deposito) {
	 * 
	 * if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) { String
	 * operacao = "Movimento de Deposito na Conta Poupanca"; BigDecimal valor =
	 * deposito; Movimento mov = new Movimento(conta, valor, operacao);
	 * 
	 * return mov;
	 * 
	 * } else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
	 * String operacao = "Movimento de Saque na Conta Poupanca"; BigDecimal
	 * valor = saque; Movimento mov = new Movimento(conta, valor, operacao);
	 * 
	 * return mov;
	 * 
	 * } else { JOptionPane.showMessageDialog(null, "Operação invalida.");
	 * return null;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public Movimento registraMovimentacaoContaEspecial(int conta, BigDecimal
	 * saque, BigDecimal deposito) {
	 * 
	 * if (saque.doubleValue() <= 0 && deposito.doubleValue() > 0) { String
	 * operacao = "Movimento de Deposito na Conta Especial"; BigDecimal valor =
	 * deposito; Movimento mov = new Movimento(conta, valor, operacao);
	 * 
	 * return mov;
	 * 
	 * } else if (saque.doubleValue() > 0 && deposito.doubleValue() <= 0) {
	 * String operacao = "Movimento de Saque na Conta Especial"; BigDecimal
	 * valor = saque; Movimento mov = new Movimento(conta, valor, operacao);
	 * 
	 * return mov;
	 * 
	 * } else { JOptionPane.showMessageDialog(null, "Operação invalida !");
	 * return null;
	 * 
	 * } } /*NO CASO DO JUROS A ENTRADA É O NUMERO DA CONTA E O CALCULO FINAL DO
	 * JUROS E RETORNA UM OBJETO PARA A LISTA DE MOVIMENTOS
	 */
	public Movimento juros(int conta, BigDecimal juros) {
		String operacao = "Movimento de Juros";
		BigDecimal valor = juros;

		Movimento mov = new Movimento(conta, valor, operacao);
		return mov;
	}
}
