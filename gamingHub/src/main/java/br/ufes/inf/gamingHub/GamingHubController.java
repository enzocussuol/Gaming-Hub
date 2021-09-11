package br.ufes.inf.gamingHub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamingHubController {
	@GetMapping("/ola")
	public String getHelloWorld(@RequestParam(name="nome") String nome,
			@RequestParam(name="n", defaultValue="1") int n) {
		String resultado = "";
		for(int i = 0; i < n; i++) resultado += "<b>Olá!</b> " + nome + "<br>";
		return resultado;
	}
}
