package br.ufes.inf.gamingHub;

public class Comentario {
	private String nomeJogo;
	private String nomeUsuario;
	private String texto;
	
	public String getNomeJogo() {
		return nomeJogo;
	}
	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "\n" + nomeUsuario + "\n" + texto + "\n" + nomeJogo;
		return str;
	}
}
