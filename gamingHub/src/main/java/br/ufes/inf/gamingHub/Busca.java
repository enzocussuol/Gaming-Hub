package br.ufes.inf.gamingHub;

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
