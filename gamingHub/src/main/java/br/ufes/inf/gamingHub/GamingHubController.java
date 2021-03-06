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
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import br.ufes.inf.gamingHub.catalogo.Catalogo;
import br.ufes.inf.gamingHub.catalogo.Comentario;
import br.ufes.inf.gamingHub.catalogo.Jogo;

/** Classe que lida com os requests do usuário
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
@Controller
public class GamingHubController{
	public static Catalogo catalogo = new Catalogo();
	public static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
	
	/** Função que chama o html da página principal com o catálogo de jogos adaptado conforme os parametros enviados
	 * 
	 * @param numPagina indica o numero da pagina atual do catálogo
	 * @param ordena indica o parametro de ordenação do catálogo
	 * @param busca indica o parametro de busca dentre os jogos do catalogo
	 * @param buscastring indica o parametro de busca dentre os jogos do catalogo
	 * @param idUnico indica o id do usuário, caso esteja logado
	 * @param logout indica se o usuário está se deslogando
	 * @param favorito indica se o catálogo deve conter apenas os jogos favoritos do usuário
	 * @param model modelo com os parametros enviados ao html
	 * @return
	 */
	@GetMapping("/GamingHub")
	public String getIndex(@RequestParam(defaultValue="0") int numPagina, 
			@RequestParam(defaultValue="0") int ordena, 
			@RequestParam(defaultValue="") Busca busca,
			@RequestParam(defaultValue="") String buscastring,
			@RequestParam(defaultValue="") String idUnico,
			@RequestParam(defaultValue="false") boolean logout,
			@RequestParam(defaultValue="false") boolean favorito,
			Model model) {
		if(logout) usuarios.remove(idUnico);
		
		Usuario usuario = null;
		if(!idUnico.equals("")) {
			usuario = usuarios.get(idUnico);
		}
		if(buscastring!="") {
			busca = new Busca();
			busca.setNomebusca(buscastring);
		}
		
		ArrayList<Jogo> jogos;
		
		if(favorito == true) jogos = usuario.getJogosFavoritos();
		else jogos = new ArrayList<Jogo>(catalogo.getJogos().values());
		

		int tamJogos = jogos.size();
		
		ArrayList<Jogo> jogosbusca = new ArrayList<Jogo>();
		for(int i=0; i < tamJogos ;i++) {
			if(jogos.get(i).dados.nome.toLowerCase().contains(busca.getNomebusca().toLowerCase())) {
				jogosbusca.add(jogos.get(i));
			}
		}
		
		int tamJogosBusca = jogosbusca.size();
		int maxPagina = (tamJogosBusca-1)/9;
		if(numPagina < 0) numPagina = 0;
		if(numPagina > maxPagina) numPagina = maxPagina;
		
		if(ordena==1) {
			Collections.sort(jogosbusca, new ComparaJogoAZ());
		}else if(ordena==2) {
			Collections.sort(jogosbusca, new ComparaJogoZA());
		}else {
			Collections.sort(jogosbusca, new ComparaJogoRec());
		}
		
		buscastring = busca.getNomebusca();
	
		ArrayList<Jogo> jogosatuais = new ArrayList<Jogo>();
		for(int i = 9*numPagina, j = 0; i < tamJogosBusca && j < 9; i++, j++) {
			jogosatuais.add(jogosbusca.get(i));
		}
		
		model.addAttribute("jogos", jogosatuais);
		model.addAttribute("numPagina", numPagina);
		model.addAttribute("ordena", ordena);
		model.addAttribute("busca", busca);
		model.addAttribute("buscastring", buscastring);
		model.addAttribute("idUnico", idUnico);
		model.addAttribute("favorito", favorito);
		model.addAttribute("usuario", usuario);
		model.addAttribute("maxpagina", maxPagina);
		
		return "index";
	}
	
	/** Função que lida com os post dos usuários na página principal
	 * 
	 * @param busca indica o parametro de busca dentre os jogos do catalogo
	 * @param numPagina indica o numero da pagina atual do catálogo
	 * @param ordena indica o parametro de ordenação do catálogo
	 * @param idUnico indica o id do usuário, caso esteja logado
	 * @param model modelo com os parametros enviados ao html
	 * @return
	 */
	@PostMapping("/GamingHub")
	public String buscaTitulo(@ModelAttribute Busca busca,
							@RequestParam(defaultValue="0") int numPagina, 
							@RequestParam(defaultValue="0") int ordena, 
							@RequestParam(defaultValue="") String idUnico,
							Model model) {
		
		model.addAttribute("numPagina", numPagina);
		model.addAttribute("ordena", ordena);
		model.addAttribute("busca", busca);
		model.addAttribute("idUnico", idUnico);
				
		return this.getIndex(numPagina, ordena, busca, "", idUnico, false, false, model);
	}
	
	/** Função que chama o html com os parametros fornecidos
	 * 
	 * @param model modelo com os parametros enviados ao html
	 * @param usuarioJaExiste indica se o usuário já esta cadastrado no site
	 * @return
	 */
	@GetMapping("/registro")
	public String getRegistro(Model model, boolean usuarioJaExiste) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarioJaExiste", usuarioJaExiste);
		
		return "registro";
	}
	
	/** Função que cadastra um novo usuário no site
	 * 
	 * @param usuario novo usuário a ser cadastrado
	 * @param model modelo com os parametros enviados ao html
	 * @return
	 * @throws CsvValidationException
	 * @throws IOException
	 */
	@PostMapping("/registro")
	public String handleRegistro(@ModelAttribute Usuario usuario, Model model) throws CsvValidationException, IOException {
		try {
			CSVReader leitor = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:arquivosDados/registros.csv")));
			String[] campos;
			
			while((campos = leitor.readNext()) != null) {
				for(String str: campos) System.out.println(str);
				
				if(usuario.getNome().equals(campos[0])) return this.getRegistro(model, true);
			}
			
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo registros.csv");
			System.exit(0);
		}
		
		try {
			FileWriter escritor = new FileWriter(ResourceUtils.getFile("classpath:arquivosDados/registros.csv"), true);
			
			String idUnico = UUID.nameUUIDFromBytes(usuario.getNome().getBytes()).toString();
			usuario.setIdUnico(idUnico);
			
			escritor.write(usuario.toString());
			escritor.close();
			System.out.println("registros.csv atualizado!");
		} catch (IOException e) {
			System.out.println("Nao foi possivel escrever em registros.csv");
		}
		
		return this.getLogin(model, false, false);
	}
	
	/** Função que chama o html da página de login
	 * 
	 * @param model modelo com os parametros enviados ao html
	 * @param senhaIncorreta indica que o a senha do usuário está incorreta
	 * @param nomeIncorreto indica que o usuário não foi encontrado nos registros da aplicação
	 * @return
	 */
	@GetMapping("/login")
	public String getLogin(Model model, boolean senhaIncorreta, boolean nomeIncorreto) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("senhaIncorreta", senhaIncorreta);
		model.addAttribute("nomeIncorreto", nomeIncorreto);
		
		return "login";
	}
	
	/**
	 * 
	 * @param usuario campo com os dados da tentativa de login do usuário
	 * @param model modelo com os parametros enviados ao html
	 * @return
	 * @throws CsvValidationException
	 * @throws IOException
	 */
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute Usuario usuario, Model model) throws CsvValidationException, IOException {
		String nome, senha, email, idUnico = "";
		try {
			CSVReader leitor = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:arquivosDados/registros.csv")));
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
						
						leitor.close();
						
						Busca busca = new Busca("");
						return this.getIndex(0,0,busca,"",idUnico,false,false,model);
					}else {
						leitor.close();
						
						return this.getLogin(model, true, false);
					}
				}
			}
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel ler do arquivo registros.csv");
			System.exit(0);
		}
		
		return this.getLogin(model, false, true);
	}
	
	/** função que chama o html da página de um jogo
	 * 
	 * @param model modelo com os parametros enviados ao html
	 * @param id indica o id do jogo a ser visitado
	 * @param idUnico indica o id do usuário
	 * @param favorito indica se o jogo está deve ser favoritado/desfavoritado ou mantido
	 * @return
	 */
	@GetMapping("/jogo")
	public String getJogo(Model model, @RequestParam String id, @RequestParam(defaultValue="") String idUnico, @RequestParam(defaultValue="0") int favorito) {
		Jogo jogo = catalogo.getJogos().get(id);
		
		Usuario usuario = null;
		if(!idUnico.equals("")) {
			usuario = usuarios.get(idUnico);
			if(usuario != null) {
				if(favorito == 1) {
					usuario.getJogosFavoritos().add(jogo);
				}else if(favorito == -1){
					usuario.getJogosFavoritos().remove(jogo);
				}else {
					if(usuario.getJogosFavoritos().contains(jogo)) favorito = 1;
					else favorito = -1;
				}
			}
		}

		model.addAttribute("jogo", jogo);
		model.addAttribute("usuario", usuario);
		model.addAttribute("comentario", new Comentario());
		model.addAttribute("idUnico", idUnico);
		model.addAttribute("favorito", favorito);
		
		return "jogo";
	}
	
	/** função que lida com os posts do usuário na página de um jogo
	 * 
	 * @param comentario contem os dados sobre um novo comentário com seu usuário,
	 * @param model modelo com os parametros enviados ao html
	 * @param id indica o id do jogo da página
	 * @param idUnico indica o id do usuário
	 * @return
	 */
	@PostMapping("/jogo")
	public String postaComentario(@ModelAttribute Comentario comentario, Model model, 
			@RequestParam String id, 
			@RequestParam(defaultValue="") String idUnico) {
		try {
			FileWriter escritor = new FileWriter(ResourceUtils.getFile("classpath:arquivosDados/comentarios.csv"), true);
			
			Usuario usuario = usuarios.get(idUnico);
			
			comentario.setImagemAutorComentario(usuario.getImgPerfil());
			comentario.setNomeAutorComentario(usuario.getNome());
			
			catalogo.getJogos().get(id).getComentarios().add(comentario);
						
			escritor.write("\n" + id + "," + comentario.getImagemAutorComentario() + "," + comentario.getNomeAutorComentario() + "," + comentario.getDescricao());
			escritor.close();
			System.out.println("comentarios.csv atualizado!");
		} catch (IOException e) {
			System.out.println("Nao foi possivel escrever em comentarios.csv");
		}
		
		return this.getJogo(model, id, idUnico, 0);
	}
}

/** Classe que extende Comparator para ordenar o catálogo de jogos em ordem alfabética
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
class ComparaJogoAZ implements Comparator<Jogo>{
	@Override
	public int compare(Jogo j1, Jogo j2) {
		return j1.dados.nome.compareToIgnoreCase(j2.dados.nome);		
	}
}

/** Classe que extende Comparator para ordenar o catálogo de jogos em ordem alfabética-invertida
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
class ComparaJogoZA implements Comparator<Jogo>{
	@Override
	public int compare(Jogo j1, Jogo j2) {
		return j2.dados.nome.compareToIgnoreCase(j1.dados.nome);		
	}
}

/** Classe que extende Comparator para ordenar o catálogo de jogos conforme o numero de recomendaçoes dos jogos
 * 
 * @author Danilo Lima e Enzo Cussuol
 *
 */
class ComparaJogoRec implements Comparator<Jogo>{
	@Override
	public int compare(Jogo j1, Jogo j2) {
		if(j1.dados.recomendacoes == null) return 1;
		if(j2.dados.recomendacoes == null) return -1;
		return j2.dados.recomendacoes.total - j1.dados.recomendacoes.total;
	}
}