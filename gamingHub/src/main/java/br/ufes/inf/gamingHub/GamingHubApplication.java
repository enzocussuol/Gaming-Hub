package br.ufes.inf.gamingHub;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufes.inf.gamingHub.catalogo.Catalogo;
import br.ufes.inf.gamingHub.catalogo.Jogo;
import obtencaoJogos.Applist;

@SpringBootApplication
public class GamingHubApplication {
	private static void obtemIdsJogos() {
		Applist appList = new Applist();
		appList = appList.deserializa("https://api.steampowered.com/ISteamApps/GetAppList/v2/");
		appList.apps.geraArquivoJogos();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GamingHubApplication.class, args);
		
		GamingHubApplication.obtemIdsJogos();
		
		Catalogo catalogo = new Catalogo();
	}
}