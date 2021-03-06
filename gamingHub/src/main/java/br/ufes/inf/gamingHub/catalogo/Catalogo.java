package br.ufes.inf.gamingHub.catalogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.util.ResourceUtils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/** Classe que contém o catálogo com os jogos disponiveis na aplicação
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
public class Catalogo {
	public HashMap<String, Jogo> jogos = new HashMap<String, Jogo>();
	
	/** Inicializa o catálogo dos jogos fazendo os request nas apis conforme os ids coletados na lista
	 * 
	 */
	public Catalogo() {
		try {
			File arquivoIdsJogos = ResourceUtils.getFile("classpath:arquivosDados/idsJogos100.txt");
			
			Jogo jogo = new Jogo();
			String id;
			
			String url = "https://store.steampowered.com/api/appdetails?lang=pt-br&appids=";
			
			BufferedReader br = new BufferedReader(new FileReader(arquivoIdsJogos));
					
		    while ((id = br.readLine()) != null) {
				Jogo novojogo = jogo.deserializa(url + id);
				
				if(novojogo != null) {
					jogos.put(id, novojogo);
				}
				
				Thread.sleep(100);
		    }
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo idsJogos.txt");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			CSVReader leitor = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:arquivosDados/comentarios.csv")));
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
	
	/** Função que retorna o mapa com os jogos do catálogo
	 * 
	 * @return
	 */
	public HashMap<String, Jogo> getJogos(){
		return jogos;
	}
}
