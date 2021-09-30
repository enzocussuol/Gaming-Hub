package br.ufes.inf.gamingHub.catalogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Catalogo {
	public HashMap<String, Jogo> jogos = new HashMap<String, Jogo>();
	
	public Catalogo() {
		try {
			File arquivoIdsJogos = new File("arquivosDados/idsJogos.txt");
			Scanner leitor = new Scanner(arquivoIdsJogos);
			
			Jogo jogo = new Jogo();
			String id;
			
			String url = "https://store.steampowered.com/api/appdetails?lang=pt-br&appids=";
			
			for(int i = 0; i < 1; i++) {
				id = leitor.nextLine();
				
				Jogo novojogo = jogo.deserializa(url + id);
				
				if(novojogo != null) {
					jogos.put(id, novojogo);		
				}
			}
			
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo idsJogos.txt");
			System.exit(0);
		}
	}
	
	public HashMap<String, Jogo> getJogos(){
		return jogos;
	}
}
