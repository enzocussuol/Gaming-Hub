package br.ufes.inf.gamingHub.catalogo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Jogo {
	@SerializedName("data")
	public DadosJogo dados;
	private LinkedList<String> comentarios = new LinkedList<String>();
	
	private static String consertaString(String str) {
		str = str.substring(1, str.length() - 1);
		str = str.substring(str.indexOf("{"));
				
		return str;
	}
	
	public Jogo deserializa(String url) {
		Gson gson = new Gson();
		String aux = RequisicoesHttp.get(url);
		if(aux.length() < 50) {
			return null;
		}
		
		return gson.fromJson(Jogo.consertaString(aux), Jogo.class);
	}
	
	public LinkedList<String> getComentarios(){
		return comentarios;
	}
	
	@Override
	public String toString() {
		return dados.toString();
	}
	
	public int compareTo(Jogo jogo) {
		return this.dados.nome.compareToIgnoreCase(jogo.dados.nome);
	}
}