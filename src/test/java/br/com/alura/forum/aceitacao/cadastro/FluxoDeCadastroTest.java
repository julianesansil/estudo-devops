package br.com.alura.forum.aceitacao.cadastro;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

public class FluxoDeCadastroTest {

	ChromeDriver browser;
	
	@Before
	public void antes() {
		System.setProperty("webdriver.chrome.driver","./chromedriver");

		browser = new ChromeDriver();
	}
	
	@After
	public void depois() {
		browser.close();
	}
	
	@Test
	public void deve_ser_capaz_de_criar_uma_conta() throws IOException, InterruptedException {
		Faker faker = new Faker();
		
		browser.get("http://localhost:8080/alura-forum/");
		
		TopicosPage paginaDeTopicos = new TopicosPage(browser);
		CadastroPage paginaDeCadastro = paginaDeTopicos.clicarNoLinkDeCadastro();
		
		String nome = faker.funnyName().name();
		String email = faker.internet().emailAddress();
		String senha = faker.internet().password();
		
		paginaDeCadastro.preencheFormulario(nome, email, senha);
		PaginaLogadaPage paginaLogadaPage = paginaDeCadastro.submeteCadastro();

		Assert.assertTrue(paginaLogadaPage.contem(nome));
	}
	
}