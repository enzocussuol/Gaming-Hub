package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.annotations.SerializedName;

/** Classe com os dados sobre o metacritic de um jogo
 * contendo a pontuação e seu url
 * 
 * @author Pichau
 *
 */
public class Metacritica {
	@SerializedName("score")
	public int pontuacao;
	@SerializedName("url")
	public String link;
}
