package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.annotations.SerializedName;

public class Jogo {
	@SerializedName("data")
	public Dados dados;
	
	@Override
	public String toString() {
		return dados.toString();
	}
}
