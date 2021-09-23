package obtencaoJogos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.annotations.SerializedName;

public class Apps {
	@SerializedName("apps")
	public ArrayList<App> apps = new ArrayList<App>();
	
	private String consertaString(String str) { // Tira primeiro campo do JSON da API appdetails
		str = str.substring(1, str.length() - 1);
		str = str.substring(str.indexOf('{'));
				
		return str;
	}
	
	public void geraArquivoJogos() {
		try {
			FileWriter escritor = new FileWriter("arquivosDados/jogos.txt");
			String url = "https://store.steampowered.com/api/appdetails?lang=pt-br&appids=";
			String respostaHttp;
			
//			Random gerador = new Random();
			
			for(int i = 0; i < apps.size(); i++) {
				respostaHttp = RequisicoesHttp.get(url + apps.get(i).appId);
				if(respostaHttp.length() < 50) continue; // Respostas com menos de 50 caracteres com certeza sao respostas de erro!
				escritor.write(this.consertaString(respostaHttp));
				
				System.out.println(respostaHttp);
//				Thread.sleep(gerador.nextInt()%2000 + 1000);
			}
			
			escritor.close();
			System.out.println("jogos.txt escrito com sucesso!");
		} catch (IOException e) {
			System.out.println("Nao foi possivel escrever em jogos.txt");
		}
	}
}
