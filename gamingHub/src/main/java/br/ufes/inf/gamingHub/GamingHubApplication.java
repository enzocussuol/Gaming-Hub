package br.ufes.inf.gamingHub;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/** Classe que iniciliaza a aplicação
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
@SpringBootApplication
public class GamingHubApplication {
	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR"));
		SpringApplication.run(GamingHubApplication.class, args);
	}
}