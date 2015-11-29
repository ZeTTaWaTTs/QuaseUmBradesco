package Conta.Registros;

public class ListaMovimentos {

	
	private No Firstmove = null;
	private No Lastmove = null;
	private int size = 0;
	
	
	
	/*public void addMoveInP (int posicao, Object move){
	 }
	 */
		
	
	
	//ADICIONA na PRIMEIRA POSIÇÃO
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
	
	//ADICIONA na ULTIMA POSIÇÃO
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
	
	/*REMOVE na posição X
	public Object removeInPosition (int posicao){
		return posicao;
	}*/
	
	//REMOVE da PRIMEIRA posiçao
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
	
	//REMOVE da ULTIMA posiçao
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
	
	/*RETORNA o movimento da 'X' POSIÇÃO
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
			throw new IllegalStateException("A lista está Vazia!");
		return Firstmove.getMove();
	}
	
	//RETORNA o ULTIMO MOVIMENTO da lista
	public Object getLast(){
		if (isEmpty())
			throw new IllegalStateException("A lista está Vazia!");
		return Lastmove.getMove();
		
	}
	
	//dando erro RETORNO verificar
	/*RETORNA o movimento que ANTECEDE. Gera um erro se o movimento é a 
	PRIMEIRA POSIÇAO*/
	public Object getNext(No Move)throws IllegalArgumentException{
		if (Move == Lastmove)
			throw new IllegalArgumentException("Não possui um movimento anterior");
		Move.getNext();
		return Move.getMove();
		
	}
	
	//dando erro RETORNO verificar
	/*RETORNA o movimento PROCEDE. Gera um erro se o movimento é a 
	uLTIMA POSIÇAO*/
	public Object getPrev(No Move)throws IllegalArgumentException{
		if (Move == Firstmove)
			throw new IllegalArgumentException("Não possui um movimento anterior");
		Move.getPrev();
		return Move.getMove();
		
	}
	
	public static void main (String []args){
		
		ListaMovimentos lista = new ListaMovimentos();
		
		lista.addInFirst("Lukas");
		lista.addInLast("João Victor");
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