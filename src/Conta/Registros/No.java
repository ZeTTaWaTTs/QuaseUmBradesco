package Conta.Registros;


public class No {

	private No next, prev;
	private Object move;
	
	public No (Object move){
		this.move = move;
		next = null;
		prev = null;
	}

	public No getNext() {
		return next;
	}

	public void setNext(No prox) {
		this.next = prox;
	}

	public No getPrev() {
		return prev;
	}

	public void setPrev(No prev) {
		this.prev = prev;
	}
	
	public Object getMove() {
		return move;
	}

	public void setMove(Object move) {
		this.move = move;
	}
}
