package Cliente;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;
public class DAOParteSeis {

	DaoCliente dao = new DaoCliente();
	
	public Cliente ordena(){
		Collections.sort(dao.consultarCliente(dao.toString()));
		//?? hashmap complicou
		return null;
	}
	public void armazenaArquivoTxt(String nomeArquivo)throws Exception{
		FileOutputStream saida;
		BufferedWriter ler;
		DaoCliente cliente;
		
		saida = new FileOutputStream(new File(nomeArquivo));
		ler = new BufferedWriter(new OutputStreamWriter(saida));
		
		ler.write(dao.//size()+"/n");
		//???
	}
	//Transforma os objetos p String
	public String toString(){
		
		return null;
	}
}
	
