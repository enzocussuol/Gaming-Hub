package br.ufes.inf.gamingHub;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufes.inf.gamingHub.catalogo.Catalogo;
import br.ufes.inf.gamingHub.catalogo.Jogo;

@Controller
public class GamingHubController{
	public static Catalogo catalogo = new Catalogo();
	
	@GetMapping("/GamingHub")
	public String getIndex(@RequestParam(defaultValue="0") int numPagina, Model model ,@RequestParam(defaultValue="0") int ordena, @RequestParam(defaultValue = "") String nome) {
		ArrayList<Jogo> jogos = new ArrayList<Jogo>(catalogo.getJogos().values());
		int tamJogos = jogos.size();
		
		if(ordena==1) {
			Collections.sort(jogos, new ComparaJogoAZ());
		}else if(ordena==2) {
			Collections.sort(jogos, new ComparaJogoZA());			
		}else {
			Collections.sort(jogos, new ComparaJogoRec());	
		}
		
		
		if(numPagina < 0) numPagina = 0;
		int maxPagina = tamJogos/9;
		
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
		
		
		model.addAttribute("jogos", jogosatuais);
		model.addAttribute("numPagina", numPagina);
		model.addAttribute("ordena", ordena);
		model.addAttribute("nome", nome);
		
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
			FileWriter escritor = new FileWriter("arquivosDados/registros.txt", true);
			escritor.write(usuario.toString());
			escritor.write("\n");
			escritor.close();
			System.out.println("registros.txt atualizado!");
		} catch (IOException e) {
			System.out.println("Nao foi possivel escrever em registros.txt");
		}
		
		catalogo.getUsuarios().put(usuario.getNome(), usuario);
	}
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login")
	public void handleLogin(@ModelAttribute Usuario usuario) {		
		HashMap<String, Usuario> usuarios = catalogo.getUsuarios();
		Usuario usuarioBuscado = usuarios.get(usuario.getNome());
		
		if(usuarioBuscado != null) {
			if(usuarioBuscado.getSenha().equals(usuario.getSenha())) {
				System.out.println("Login realizado com sucesso!");
				return;
			}
		}
		
		System.out.println("Um erro aconteceu ao logar...");
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