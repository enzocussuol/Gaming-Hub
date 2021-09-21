package br.ufes.inf.gamingHub;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RequisicoesHttp {
	public static String get(String url) {
		String respostaHttp = null;
		
		try {
			CloseableHttpClient clienteHttp = HttpClients.createDefault();
			HttpGet getHttp = new HttpGet(url);
			
			System.out.println("Fazendo download...");
			
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
