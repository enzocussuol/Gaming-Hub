package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import obtencaoJogos.RequisicoesHttp;

public class Jogo {
	@SerializedName("data")
	public Dados dados;
	
	public Jogo deserializa(String strJson) {
		Gson gson = new Gson();
		
		return gson.fromJson(strJson, Jogo.class);
	}
	
	@Override
	public String toString() {
		return dados.toString();
	}
}
