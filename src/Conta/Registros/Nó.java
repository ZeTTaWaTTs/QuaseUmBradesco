package Conta.Registros;


public class N� {

	private N� next, prev;
	private Registro move;
	
	public N� (Registro move){
		this.move = move;
		next = null;
		prev = null;
	}

	public N� getNext() {
		return next;
	}

	public void setNext(N� prox) {
		this.next = prox;
	}

	public N� getPrev() {
		return prev;
	}

	public void setPrev(N� prev) {
		this.prev = prev;
	}
	
	public Registro getMove() {
		return move;
	}

	public void setMove(Registro move) {
		this.move = move;
	}
}
