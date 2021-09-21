package br.ufes.inf.gamingHub;

import java.util.LinkedList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufes.inf.gamingHub.catalogo.Jogo;

@SpringBootApplication
public class GamingHubApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GamingHubApplication.class, args);
		
		LinkedList<Jogo> jogos = GamingHubController.catalogo.getJogos();
		
		Jogo jogo = new Jogo();
				
		jogo = jogo.deserializa("https://store.steampowered.com/api/appdetails?appids=220&lang=pt-br");
		jogos.add(jogo);
		
		jogo = jogo.deserializa("https://store.steampowered.com/api/appdetails?appids=730&lang=pt-br");
		jogos.add(jogo);
		
		jogo = jogo.deserializa("https://store.steampowered.com/api/appdetails?appids=578080&lang=pt-br");
		jogos.add(jogo);
		
		jogo = jogo.deserializa("https://store.steampowered.com/api/appdetails?appids=1172470&lang=pt-br");
		jogos.add(jogo);
		
		jogo = jogo.deserializa("https://store.steampowered.com/api/appdetails?appids=1085660&lang=pt-br");
		jogos.add(jogo);
		
		jogo = jogo.deserializa("https://store.steampowered.com/api/appdetails?appids=271590&lang=pt-br");
		jogos.add(jogo);
	}
}