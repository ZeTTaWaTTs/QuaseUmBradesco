package Conta.Registros;

public class ListaMovimentos {

	
	private No Firstmove = null;
	private No Lastmove = null;
	private int size = 0;
	
	
	
	/*public void addMoveInP (int posicao, Object move){
	 }
	 */
		
	
	
	//ADICIONA na PRIMEIRA POSI��O
	public void addInFirst (Object move){
		No newMove = new No(move);
		
		if(isEmpty()){
			Firstmove = newMove;
			Lastmove = newMove;
		}else{
			Firstmove.setPrev(newMove); //Firstmove <---- newMove
			newMove.setNext(Firstmove); //newMove ----> Firstmove
			Firstmove = newMove; //Firstmove = newMove
		}
		size++;
	}
	
	//ADICIONA na ULTIMA POSI��O
	public void addInLast (Object move){
		No newMove = new No(move);
		
			if(isEmpty()){
				Firstmove = newMove;
				Lastmove = newMove;
			}else{
				newMove.setPrev(Lastmove);	//newMove ----> Firstmove
				Lastmove.setNext(newMove);	//Firstmove <---- newMove
				Lastmove = newMove;	//Lastmove = newMove
			}
		size++;
	}
	
	/*REMOVE na posi��o X
	public Object removeInPosition (int posicao){
		return posicao;
	}*/
	
	//REMOVE da PRIMEIRA posi�ao
	public Object removeInFirst (){
		if(isEmpty()) return null;
		
		No aux = Firstmove;
		
		if(Firstmove.getNext() != null){
			Firstmove = Firstmove.getNext();
			Firstmove.getPrev().setNext(null);
		}else{
			Firstmove = null;
			Lastmove = null;
		}
		size--;
		
		return aux.getMove();
	}
	
	//REMOVE da ULTIMA posi�ao
		public Object removeInLast (){
			if(isEmpty()) return null;
			
			No aux = Firstmove;
			
			if(Firstmove.getNext() != null){
				Firstmove = Firstmove.getNext();
				Firstmove.getPrev().setNext(null);
			}else{
				Firstmove = null;
				Lastmove = null;
			}
			size--;
			
			return aux.getMove();
		}
	
	/*RETORNA o movimento da 'X' POSI��O
	public Object getMove (int posicao){
		return posicao;
	}*/
	
	//RETORNA o TAMANHO
	public int size (){
		return size;
	}
	
	//VERIFICA se esta vazia
	public boolean isEmpty(){
		return size == 0;
	}
	
	//RETORNA o PRMEIRO MOVIMENTO da lista
	public Object getFirst()throws IllegalStateException{
		if (isEmpty())
			throw new IllegalStateException("A lista est� Vazia!");
		return Firstmove.getMove();
	}
	
	//RETORNA o ULTIMO MOVIMENTO da lista
	public Object getLast(){
		if (isEmpty())
			throw new IllegalStateException("A lista est� Vazia!");
		return Lastmove.getMove();
		
	}
	
	//dando erro RETORNO verificar
	/*RETORNA o movimento que ANTECEDE. Gera um erro se o movimento � a 
	PRIMEIRA POSI�AO*/
	public Object getNext(No Move)throws IllegalArgumentException{
		if (Move == Lastmove)
			throw new IllegalArgumentException("N�o possui um movimento anterior");
		Move.getNext();
		return Move.getMove();
		
	}
	
	//dando erro RETORNO verificar
	/*RETORNA o movimento PROCEDE. Gera um erro se o movimento � a 
	uLTIMA POSI�AO*/
	public Object getPrev(No Move)throws IllegalArgumentException{
		if (Move == Firstmove)
			throw new IllegalArgumentException("N�o possui um movimento anterior");
		Move.getPrev();
		return Move.getMove();
		
	}
	
	public static void main (String []args){
		
		ListaMovimentos lista = new ListaMovimentos();
		
		lista.addInFirst("Lukas");
		lista.addInLast("Jo�o Victor");
		lista.addInFirst("Marcus");
		
		System.out.println("Primeiro: " + lista.getFirst());
		System.out.println("Ultimo: " + lista.getLast());
		
		lista.removeInFirst();
		
		System.out.println("Primeiro:" + lista.getFirst());
		System.out.println("Ultimo: " + lista.getLast());
		
		lista.removeInLast();
		
		System.out.println("Primeiro: " + lista.getFirst());
		System.out.println("Ultimo: " + lista.getLast());

		
	}
}