package br.ufes.inf.gamingHub.catalogo;

import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/** Classe que lida com os jogos da aplicação
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class Jogo {
	@SerializedName("data")
	public DadosJogo dados;
	private LinkedList<Comentario> comentarios = new LinkedList<Comentario>();
	
	/** Corrige a string para ser deserializada 
	 * 
	 * @param str
	 * @return
	 */
	private static String consertaString(String str) {
		str = str.substring(1, str.length() - 1);
		str = str.substring(str.indexOf("{"));
				
		return str;
	}
	
	/** função que lida com a deserialização do gson a partir da url com a api com dados do jogo
	 * 
	 * @param url endereço da api a ser deserializada
	 * @return
	 */
	public Jogo deserializa(String url) {
		Gson gson = new Gson();
		String aux = RequisicoesHttp.get(url);
		if(aux.length() < 50) {
			return null;
		}
		
		return gson.fromJson(Jogo.consertaString(aux), Jogo.class);
	}
	
	/** Função que retorna a lista de comentários sobre o jogo
	 * 
	 * @return
	 */
	public LinkedList<Comentario> getComentarios(){
		return comentarios;
	}
	
	@Override
	public String toString() {
		return dados.toString();
	}
	
	/** Função que compara os nomes entre jogos
	 * 
	 * @param jogo jogo a ser comparado
	 * @return
	 */
	public int compareTo(Jogo jogo) {
		return this.dados.nome.compareToIgnoreCase(jogo.dados.nome);
	}
}