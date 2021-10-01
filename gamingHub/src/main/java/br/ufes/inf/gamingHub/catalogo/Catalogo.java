package br.ufes.inf.gamingHub.catalogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import br.ufes.inf.gamingHub.Usuario;

public class Catalogo {
	public HashMap<String, Jogo> jogos = new HashMap<String, Jogo>();
	
	public Catalogo() {
		try {
			File arquivoIdsJogos = new File("arquivosDados/idsJogos.txt");
			Scanner leitor = new Scanner(arquivoIdsJogos);
			
			Jogo jogo = new Jogo();
			String id;
			
			String url = "https://store.steampowered.com/api/appdetails?lang=pt-br&appids=";
			
			for(int i = 0; i < 2; i++) {
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
		
		try {
			CSVReader leitor = new CSVReader(new FileReader("arquivosDados/comentarios.csv"));
			String[] campos;
			
			leitor.readNext();
			while((campos = leitor.readNext()) != null) {
				Jogo jogo = jogos.get(campos[0]);
				jogo.getComentarios().add(new Comentario(campos[1], campos[2], campos[3]));
			}
			
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo comentarios.csv");
			System.exit(0);
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Jogo> getJogos(){
		return jogos;
	}
}
