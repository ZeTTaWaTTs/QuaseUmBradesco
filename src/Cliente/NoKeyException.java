package Cliente;

public class NoKeyException extends Exception{

	public NoKeyException() {
		super("N�o foi encontrada uma chave v�lida para o meliante em quest�o");
	}
}
