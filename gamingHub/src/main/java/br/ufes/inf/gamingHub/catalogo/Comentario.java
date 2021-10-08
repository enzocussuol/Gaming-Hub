package br.ufes.inf.gamingHub.catalogo;

/** Classe com os dados sobre um comentário feito em um jogo
 * contendo dados sobre o autor do comentário e o texto com o comentário em si
 * 
 * @author Danilo Lima e Enzo cussuol
 *
 */
public class Comentario {
	private String imagemAutorComentario;
	private String nomeAutorComentario;
	private String descricao;
	
	public Comentario() {
		
	}
	
	public Comentario(String imagemAutorComentario, String nomeAutorComentario, String descricao) {
		this.imagemAutorComentario = imagemAutorComentario;
		this.nomeAutorComentario = nomeAutorComentario;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagemAutorComentario() {
		return imagemAutorComentario;
	}

	public void setImagemAutorComentario(String imagemAutorComentario) {
		this.imagemAutorComentario = imagemAutorComentario;
	}

	public String getNomeAutorComentario() {
		return nomeAutorComentario;
	}

	public void setNomeAutorComentario(String nomeAutorComentario) {
		this.nomeAutorComentario = nomeAutorComentario;
	}
}
