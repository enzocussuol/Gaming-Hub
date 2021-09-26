package br.ufes.inf.gamingHub;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufes.inf.gamingHub.catalogo.Catalogo;

@Controller
public class GamingHubController{
	public static Catalogo catalogo = new Catalogo();
	public static Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>(); // Tem q preencher essa hash a partir de registros.txt
	
	@GetMapping("/GamingHub")
	public String getIndex(Model model) {
		model.addAttribute("jogos", catalogo.getJogos().values());
		
		return "index";
	}
	
	@GetMapping("/jogo")
	public String getJogo(@RequestParam String nome) {
		return "jogo";
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
			escritor.close();
			System.out.println("registros.txt atualizado!");
		} catch (IOException e) {
			System.out.println("Nao foi possivel escrever em registros.txt");
		}
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
}