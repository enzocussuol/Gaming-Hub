package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.annotations.SerializedName;
/** Classe com dados sobre o preço de um jogo
 * contendo a porcentagem em caso desconto e o preco atual do jogo
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class Preco {
	@SerializedName("discount_percent")
	public float desconto;
	@SerializedName("final_formatted")
	public String precofinal;
}
