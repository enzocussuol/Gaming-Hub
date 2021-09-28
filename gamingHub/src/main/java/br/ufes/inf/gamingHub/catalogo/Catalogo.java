package br.ufes.inf.gamingHub.catalogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import br.ufes.inf.gamingHub.Usuario;

public class Catalogo {
	public HashMap<String, Jogo> jogos = new HashMap<String, Jogo>();
	public HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
	
	public Catalogo() {
		try {
			File arquivoIdsJogos = new File("arquivosDados/idsJogos.txt");
			Scanner leitor = new Scanner(arquivoIdsJogos);
			
			Jogo jogo = new Jogo();
			String id;
			
			String url = "https://store.steampowered.com/api/appdetails?lang=pt-br&appids=";
			
			for(int i = 0; i < 18; i++) {
				id = leitor.nextLine();
				jogos.put(id, jogo.deserializa(url + id));
				
				Thread.sleep(1000);
			}
			
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo idsJogos.txt");
			System.exit(0);
		} catch (InterruptedException e) {
			System.out.println("Erro ao fazer a thread dormir...");
			System.exit(0);
		}
		
		try {
			File arquivoRegistros = new File("arquivosDados/registros.txt");
			Scanner leitor = new Scanner(arquivoRegistros);
			
			String nome, email, senha;
						
			while(leitor.hasNextLine()) {	
				nome = leitor.nextLine();
				
				if(nome.equals("\n")) break;
				
				email = leitor.nextLine();
				senha = leitor.nextLine();
				leitor.nextLine();
				
				usuarios.put(nome, new Usuario(nome, email, senha));
			}
			
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo registros.txt");
			System.exit(0);
		}
	}
	
	public HashMap<String, Jogo> getJogos(){
		return jogos;
	}
	
	public HashMap<String, Usuario> getUsuarios(){
		return usuarios;
	}
}
