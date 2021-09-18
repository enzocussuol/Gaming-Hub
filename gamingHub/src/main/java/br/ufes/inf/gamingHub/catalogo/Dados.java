package br.ufes.inf.gamingHub.catalogo;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Dados {
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
}
