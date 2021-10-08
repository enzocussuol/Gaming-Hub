package br.ufes.inf.gamingHub.catalogo;

import com.google.gson.annotations.SerializedName;

/** Classe com dados sobre o gênero de um jogo
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class Genero {
	@SerializedName("id")
	public int id;
	@SerializedName("description")
	public String descricao;
}
