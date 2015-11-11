package Cliente;

public class NoKeyException extends Exception{

	public NoKeyException() {
		super("Não foi encontrada uma chave válida para o meliante em questão");
	}
}
