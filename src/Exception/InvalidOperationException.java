package Exception;

public class InvalidOperationException extends Exception {

	public InvalidOperationException(){
		super("Opera��o invalida. Sem valores validos para saque e deposito.");
	}
}
