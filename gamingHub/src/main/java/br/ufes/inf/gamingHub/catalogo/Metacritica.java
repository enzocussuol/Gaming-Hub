package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.annotations.SerializedName;

public class Metacritica {
	@SerializedName("score")
	public int pontuacao;
	@SerializedName("url")
	public String link;
}
