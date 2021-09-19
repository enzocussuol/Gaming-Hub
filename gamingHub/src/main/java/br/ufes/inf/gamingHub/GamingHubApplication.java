package br.ufes.inf.gamingHub;

import java.util.LinkedList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import br.ufes.inf.gamingHub.catalogo.Appdetails;
import br.ufes.inf.gamingHub.catalogo.Jogo;

@SpringBootApplication
public class GamingHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamingHubApplication.class, args);
		
		LinkedList<Jogo> jogos = GamingHubController.catalogo.getJogos();
		
		Gson gson = new Gson();
		
		Appdetails app = gson.fromJson(RequisicoesHttp.get("https://store.steampowered.com/api/appdetails?appids=220&lang=pt-br"), Appdetails.class);
		jogos.add(app.jogo);
		
		app = gson.fromJson(RequisicoesHttp.get("https://store.steampowered.com/api/appdetails?appids=730&lang=pt-br"), Appdetails.class);
		jogos.add(app.jogo);

		System.out.println("\n");
		for(int i = 0; i < jogos.size(); i++) {
			System.out.println(jogos.get(i).toString());
		}	
	}
}