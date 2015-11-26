package Conta.Registros;


public class Nó {

	private Nó next, prev;
	private Registro move;
	
	public Nó (Registro move){
		this.move = move;
		next = null;
		prev = null;
	}

	public Nó getNext() {
		return next;
	}

	public void setNext(Nó prox) {
		this.next = prox;
	}

	public Nó getPrev() {
		return prev;
	}

	public void setPrev(Nó prev) {
		this.prev = prev;
	}
	
	public Registro getMove() {
		return move;
	}

	public void setMove(Registro move) {
		this.move = move;
	}
}
