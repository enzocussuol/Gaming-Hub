package obtencaoJogos;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Applist {
	@SerializedName("applist")
	public Apps apps;
	
	public Applist deserializa(String url) {
		Gson gson = new Gson();
		
		return gson.fromJson(RequisicoesHttp.get(url), Applist.class);
	}
}
