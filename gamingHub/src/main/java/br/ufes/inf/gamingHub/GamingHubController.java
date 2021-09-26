package br.ufes.inf.gamingHub;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
}