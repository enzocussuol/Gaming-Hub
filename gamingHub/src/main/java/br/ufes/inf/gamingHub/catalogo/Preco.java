package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.annotations.SerializedName;

public class Preco {
	@SerializedName("discount_percent")
	public float desconto;
	@SerializedName("final_formatted")
	public String precofinal;
}
