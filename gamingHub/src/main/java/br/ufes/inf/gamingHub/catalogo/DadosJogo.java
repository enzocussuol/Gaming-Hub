package br.ufes.inf.gamingHub.catalogo;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DadosJogo {
	@SerializedName("type")
	public String tipo;
	@SerializedName("name")
	public String nome;
	@SerializedName("steam_appid")
	public int id;
	@SerializedName("is_free")
	public boolean gratuito;
	@SerializedName("detailed_description")
	public String descricaoDetalhada;
	@SerializedName("about_the_game")
	public String sobreOJogo;
	@SerializedName("short_description")
	public String descricaoCurta;
	@SerializedName("header_image")
	public String imagem;
	@SerializedName("website")
	public String website;
	@SerializedName("developers")
	public ArrayList<String> desenvolvedores;
	@SerializedName("metacritic")
	public Metacritica metacritica;
	@SerializedName("genres")
	public ArrayList<Genero> genero = new ArrayList<Genero>();
	@SerializedName("screenshots")
	public ArrayList<Imagens> imagens = new ArrayList<Imagens>();
	@SerializedName("recommendations")
	public Recomendacoes recomendacoes;
	
	@Override
	public String toString() {
		String str = "";
		
		str += "tipo: " + tipo + "\n";
		str += "nome: " + nome + "\n";
		str += "id: " + id + "\n";
		str += "gratuito: " + gratuito + "\n";
		str += "descricaoDetalhada: " + descricaoDetalhada + "\n";
		str += "sobreOJogo: " + sobreOJogo + "\n";
		str += "descricaoCurta: " + descricaoCurta + "\n";
		str += "imagem: " + imagem + "\n";
		str += "website: " + website + "\n";
		str += "desenvolvedores: ";
		
		for(int i = 0; i < desenvolvedores.size(); i++) {
			str += desenvolvedores.get(i) + " ";
		}
		str += "\n";
		
		return str;
	}
}
