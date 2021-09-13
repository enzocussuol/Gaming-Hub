package br.ufes.inf.gamingHub;

import java.util.LinkedList;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private LinkedList<Jogo> jogosFavoritos = new LinkedList<Jogo>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
