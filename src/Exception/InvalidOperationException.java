package Exception;

public class InvalidOperationException extends Exception {

	public InvalidOperationException(){
		super("Operação invalida. Sem valores validos para saque e deposito.");
	}
}
