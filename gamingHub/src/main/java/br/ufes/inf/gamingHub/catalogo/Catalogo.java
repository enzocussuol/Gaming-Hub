package br.ufes.inf.gamingHub.catalogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Catalogo {
	private LinkedList<Jogo> jogos = new LinkedList<Jogo>();

	public Catalogo() {
		Jogo jogo = new Jogo();
		
		try {
			File arquivoJogos = new File("arquivosDados/jogos.txt");
			Scanner leitor = new Scanner(arquivoJogos);
			
			while(leitor.hasNextLine()) {
				String strJson = leitor.nextLine();
				
				jogo = jogo.deserializa(strJson);
				if(jogo.dados.tipo.equals("game")) {
					jogos.add(jogo);
					System.out.println("Adicionei um jogo!");
				}
			}
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo jogos.txt");
		}
	}
	
	public LinkedList<Jogo> getJogos(){
		return jogos;
	}
}
