package Exception;

public class NoKeyException extends Exception{

	public NoKeyException() {
		super("Não foi encontrada uma chave válida");
	}
}
