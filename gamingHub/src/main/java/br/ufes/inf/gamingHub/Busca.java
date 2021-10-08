package br.ufes.inf.gamingHub;

/** Classe que contem as informações sobre uma busca por nome de um jogo
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class Busca {
	private String nomebusca;
	
	public Busca() {
		
	}
	public Busca(String nomebusca) {
		this.setNomebusca(nomebusca);
	}
	public String getNomebusca() {
		return nomebusca;
	}
	public void setNomebusca(String nomebusca) {
		this.nomebusca = nomebusca;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "\n" + nomebusca;
		return str;
	}
}
