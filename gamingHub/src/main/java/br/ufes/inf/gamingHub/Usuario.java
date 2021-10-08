package br.ufes.inf.gamingHub;

import java.util.ArrayList;

import br.ufes.inf.gamingHub.catalogo.Jogo;

/** Classe com os dados dos usuários logados da aplicação
 *  Contendo diversos campos sendo os principais: nome, email, senha e idUnico
 * 
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private String confirmacaoSenha;
	private String idUnico;
	private String imgPerfil;
	private ArrayList<Jogo> jogosFavoritos = new ArrayList<Jogo>();
	
	public Usuario() {
		
	}
	
	/** Função que inicializa um novo usuário
	 * 
	 * @param nome contém o nome do usuário
	 * @param email contém o e-mail do usuário
	 * @param senha contém a senha do usuário
	 * @param idUnico contém o id do usuário utilizado como parametro nas urls
	 */
	public Usuario(String nome, String email, String senha, String idUnico) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.idUnico = idUnico;
		this.imgPerfil = "https://robohash.org/" + email;
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

	public String getImgPerfil() {
		return imgPerfil;
	}

	public void setImgPerfil(String imgperfil) {
		this.imgPerfil = imgperfil;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		str += "\n" + nome + "," + senha + "," + email + "," + idUnico;
		
		return str;
	}
	
	/** Função que retorna a lista com os jogos favoritos do usuário
	 * 
	 * @return
	 */
	public ArrayList<Jogo> getJogosFavoritos() {
		return jogosFavoritos;
	}
}
