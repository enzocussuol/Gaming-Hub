package br.ufes.inf.gamingHub;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import br.ufes.inf.gamingHub.catalogo.Appdetails;

@SpringBootApplication
public class GamingHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamingHubApplication.class, args);
		
		String respostaHttp = null;
		
		try {
			String url = "https://store.steampowered.com/api/appdetails?appids=220";
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
		
		Gson gson = new Gson();
		Appdetails app = gson.fromJson(respostaHttp, Appdetails.class);
		
		GamingHubController.catalogo.getJogos().add(app.jogo);
	}
}