package br.ufes.inf.gamingHub;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private String confirmacaoSenha;
	private String idUnico;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String senha, String idUnico) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.idUnico = idUnico;
	}
	
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
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getIdUnico() {
		return idUnico;
	}

	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		str += "\n" + nome + ", " + senha + ", " + email + ", " + idUnico;
		
		return str;
	}
}
