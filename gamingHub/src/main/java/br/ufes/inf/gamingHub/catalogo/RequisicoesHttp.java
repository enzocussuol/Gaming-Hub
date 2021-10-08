package br.ufes.inf.gamingHub.catalogo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/** Classe que lida com as requisições Http
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class RequisicoesHttp {
	/** Função que lida com as requisições HTTP
	 * 
	 * @param url indica o endereço a ser lidado
	 * @return
	 */
	public static String get(String url) {
		String respostaHttp = null;
		
		try {
			CloseableHttpClient clienteHttp = HttpClients.createDefault();
			HttpGet getHttp = new HttpGet(url);

			System.out.print("Fazendo download na url: ");
			System.out.println(url);
			
			CloseableHttpResponse resposta = clienteHttp.execute(getHttp);
			HttpEntity entidade = resposta.getEntity();
			
			System.out.println("Download terminado!");
			
			respostaHttp = EntityUtils.toString(entidade);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return respostaHttp;
	}
}
