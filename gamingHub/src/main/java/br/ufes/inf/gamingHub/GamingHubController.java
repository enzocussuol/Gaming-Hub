package br.ufes.inf.gamingHub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GamingHubController{
	@GetMapping("/GamingHub")
	public String getIndex(){
		return "index";
	}
}