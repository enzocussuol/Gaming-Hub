package br.ufes.inf.gamingHub;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
	public String getIndex(@RequestParam(defaultValue="0") int numPagina, Model model) {
		if(numPagina < 0) numPagina = 0;
		else if(numPagina > 16) numPagina = 16;
		
		ArrayList<Jogo> jogos = new ArrayList<Jogo>(catalogo.getJogos().values());
		
		model.addAttribute("jogos", jogos);
		model.addAttribute("numPagina", numPagina);
		
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