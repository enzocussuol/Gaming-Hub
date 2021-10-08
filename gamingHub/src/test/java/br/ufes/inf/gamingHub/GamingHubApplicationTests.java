package br.ufes.inf.gamingHub;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GamingHubController.class)
class GamingHubApplicationTests {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private GamingHubController controller;
	
	@Test
	void testGetsBasicos() throws Exception {
		assertThat(controller).isNotNull();
		
		mvc.perform(get("/GamingHub"))
			.andExpect(status().isOk());
		
		mvc.perform(get("/registro"))
			.andExpect(status().isOk());
		
		mvc.perform(get("/login"))
			.andExpect(status().isOk());
		
		mvc.perform(get("/jogo?id=730"))
			.andExpect(status().isOk());
	}
}
