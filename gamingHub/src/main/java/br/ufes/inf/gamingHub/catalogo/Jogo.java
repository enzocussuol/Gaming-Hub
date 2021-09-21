package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import br.ufes.inf.gamingHub.RequisicoesHttp;

public class Jogo {
	@SerializedName("data")
	public Dados dados;
	
	private String consertaString(String str) {
		str = str.substring(1, str.length() - 1);
		str = str.substring(str.indexOf('{'));
				
		return str;
	}
	
	public Jogo deserializa(String url) {
		Gson gson = new Gson();
		
		return gson.fromJson(this.consertaString(RequisicoesHttp.get(url)), Jogo.class);
	}
	
	@Override
	public String toString() {
		return dados.toString();
	}
}
