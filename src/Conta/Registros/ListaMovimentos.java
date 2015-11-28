package Conta.Registros;

public class ListaMovimentos {

	
	private N� Firstmove = null;
	private N� Lastmove = null;
	private int size = 0;
	
	
	
	/*public void addMoveInP (int posicao, Object move){
	 }
	 */
		
	
	
	//ADICIONA na PRIMEIRA POSI��O
	public void addInFirst (Object move){
		N� newMove = new N�(move);
		
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
		N� newMove = new N�(move);
		
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
		
		N� aux = Firstmove;
		
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
			
			N� aux = Firstmove;
			
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
	public N� getFirst()throws IllegalStateException{
		if (isEmpty())
			throw new IllegalStateException("A lista est� Vazia!");
		return Firstmove.getNext();
	}
	
	//RETORNA o ULTIMO MOVIMENTO da lista
	public N� getLast(){
		if (isEmpty())
			throw new IllegalStateException("A lista est� Vazia!");
		return Lastmove.getPrev();
		
	}
	
	/*RETORNA o movimento que ANTECEDE. Gera um erro se o movimento � a 
	PRIMEIRA POSI�AO*/
	public N� getNext(N� Move)throws IllegalArgumentException{
		if (Move == Lastmove)
			throw new IllegalArgumentException("N�o possui um movimento anterior");
		return Move.getNext();
		
	}
	
	/*RETORNA o movimento PROCEDE. Gera um erro se o movimento � a 
	uLTIMA POSI�AO*/
	public N� getPrev(N� Move)throws IllegalArgumentException{
		if (Move == Firstmove)
			throw new IllegalArgumentException("N�o possui um movimento anterior");
		return Move.getPrev();
		
	}
}