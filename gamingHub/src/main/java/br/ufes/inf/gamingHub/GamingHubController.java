package br.ufes.inf.gamingHub;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import br.ufes.inf.gamingHub.catalogo.Catalogo;
import br.ufes.inf.gamingHub.catalogo.Jogo;

@Controller
public class GamingHubController{
	public static Catalogo catalogo = new Catalogo();
	public static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
	
	@GetMapping("/GamingHub")
	public String getIndex(@RequestParam(defaultValue="0") int numPagina, 
			@RequestParam(defaultValue="0") int ordena, 
			@RequestParam(defaultValue="") String nome, 
			@RequestParam(defaultValue="") String idUnico,
			Model model) {
		ArrayList<Jogo> jogos = new ArrayList<Jogo>(catalogo.getJogos().values());
		
		int tamJogos = jogos.size();
		int maxPagina = tamJogos/9;
		
		if(ordena==1) {
			Collections.sort(jogos, new ComparaJogoAZ());
		}else if(ordena==2) {
			Collections.sort(jogos, new ComparaJogoZA());			
		}else {
			Collections.sort(jogos, new ComparaJogoRec());	
		}
		
		if(numPagina < 0) numPagina = 0;
		if(numPagina > maxPagina) numPagina = maxPagina;
		
		ArrayList<Jogo> jogosatuais = new ArrayList<Jogo>();
		for(int i = 0, j = 0; i < 9; i++) {
			if(j + 9*numPagina < tamJogos) {
				
				if(jogos.get(i+9*numPagina).dados.nome.contains(nome)) {
					jogosatuais.add(jogos.get(i+9*numPagina));		
					j++;
				}
				
			}else break;
		}
		
		Usuario usuario = null;
		if(!idUnico.equals("")) {
			usuario = usuarios.get(idUnico);
		}
		
		model.addAttribute("jogos", jogosatuais);
		model.addAttribute("numPagina", numPagina);
		model.addAttribute("ordena", ordena);
		model.addAttribute("nome", nome);
		model.addAttribute("usuario", usuario);
		model.addAttribute("idUnico", idUnico);
		
		return "index";
	}
		
	@GetMapping("/registro")
	public String getRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/registro")
	public void handleRegistro(@ModelAttribute Usuario usuario) {
		try {
			FileWriter escritor = new FileWriter("arquivosDados/registros.csv", true);
			
			String idUnico = UUID.nameUUIDFromBytes(usuario.getNome().getBytes()).toString();
			usuario.setIdUnico(idUnico);
			
			escritor.write(usuario.toString());
			escritor.close();
			System.out.println("registros.csv atualizado!");
		} catch (IOException e) {
			System.out.println("Nao foi possivel escrever em registros.csv");
		}
	}
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute Usuario usuario, Model model) throws CsvValidationException, IOException {
		String nome, senha, email, idUnico = "";
		
		try {
			CSVReader leitor = new CSVReader(new FileReader("arquivosDados/registros.csv"));
			String[] campos;
			
			leitor.readNext();
			while((campos = leitor.readNext()) != null) {
				nome = campos[0];
				if(usuario.getNome().equals(nome)) {
					senha = campos[1];
					if(usuario.getSenha().equals(senha)) {
						email = campos[2];
						idUnico = campos[3];
						
						usuarios.put(idUnico, new Usuario(nome, email, senha, idUnico));
						
						System.out.print("Usuario ativo adicionado! id = ");
						System.out.println(idUnico);
						break;
					}
				}
			}
			
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo registros.csv");
			System.exit(0);
		}
		return this.getIndex(0,0,"",idUnico,model);
	}
	
	@GetMapping("/jogo")
	public String getJogo(Model model, @RequestParam String id) {		
		Jogo jogo = catalogo.getJogos().get(id);

		model.addAttribute("jogo", jogo);
		
		return "jogo";
	}
}

class ComparaJogoAZ implements Comparator<Jogo>{
	@Override
	public int compare(Jogo j1, Jogo j2) {
		return j1.dados.nome.compareToIgnoreCase(j2.dados.nome);		
	}
}

class ComparaJogoZA implements Comparator<Jogo>{
	@Override
	public int compare(Jogo j1, Jogo j2) {
		return j2.dados.nome.compareToIgnoreCase(j1.dados.nome);		
	}
}

class ComparaJogoRec implements Comparator<Jogo>{
	@Override
	public int compare(Jogo j1, Jogo j2) {
		return j2.dados.recomendacoes.total - j1.dados.recomendacoes.total;
	}
}