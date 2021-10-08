package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.annotations.SerializedName;


/** Classe com dados sobre imagens de um jogo
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class Imagens {
	@SerializedName("path_thumbnail")
	public String thumbnail;
	@SerializedName("path_full")
	public String imgCompleta;
}
