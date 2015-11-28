package Conta.Registros;

public class ListaMovimentos {

	
	private Nó Firstmove = null;
	private Nó Lastmove = null;
	private int size = 0;
	
	
	
	/*public void addMoveInP (int posicao, Object move){
	 }
	 */
		
	
	
	//ADICIONA na PRIMEIRA POSIÇÃO
	public void addInFirst (Object move){
		Nó newMove = new Nó(move);
		
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
		Nó newMove = new Nó(move);
		
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
		
		Nó aux = Firstmove;
		
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
			
			Nó aux = Firstmove;
			
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
	public Nó getFirst()throws IllegalStateException{
		if (isEmpty())
			throw new IllegalStateException("A lista está Vazia!");
		return Firstmove.getNext();
	}
	
	//RETORNA o ULTIMO MOVIMENTO da lista
	public Nó getLast(){
		if (isEmpty())
			throw new IllegalStateException("A lista está Vazia!");
		return Lastmove.getPrev();
		
	}
	
	/*RETORNA o movimento que ANTECEDE. Gera um erro se o movimento é a 
	PRIMEIRA POSIÇAO*/
	public Nó getNext(Nó Move)throws IllegalArgumentException{
		if (Move == Lastmove)
			throw new IllegalArgumentException("Não possui um movimento anterior");
		return Move.getNext();
		
	}
	
	/*RETORNA o movimento PROCEDE. Gera um erro se o movimento é a 
	uLTIMA POSIÇAO*/
	public Nó getPrev(Nó Move)throws IllegalArgumentException{
		if (Move == Firstmove)
			throw new IllegalArgumentException("Não possui um movimento anterior");
		return Move.getPrev();
		
	}
}