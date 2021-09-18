package br.ufes.inf.gamingHub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufes.inf.gamingHub.catalogo.Catalogo;

@Controller
public class GamingHubController{
	public static Catalogo catalogo = new Catalogo();
	
	@GetMapping("/GamingHub")
	public String getIndex(Model model) {
		model.addAttribute("jogos", catalogo.getJogos());
		return "index";
	}
	
	@GetMapping("/jogo")
	public String getJogo(@RequestParam String nome) {
		return "jogo";
	}
}