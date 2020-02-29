package br.com.alura.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TopicoTest {

	Categoria subcategoria;
	Curso curso;
	Usuario autor;
	Topico topico;
	
	@Before
	public void before() {
		// Dado
		subcategoria = new Categoria("Testes");
		curso = new Curso("DevOps", subcategoria);
		autor = new Usuario("Juliane", "juliane@teste.com", "123");
		topico = new Topico("Testes unitários", "Como faço testes unitários em Java?", autor, curso);
	}
	
	@Test
	public void o_estado_inicial_de_um_topico_deve_ser_nao_respondido() {
		// Então
		assertEquals(StatusTopico.NAO_RESPONDIDO, topico.getStatus());
	}
	
	@Test
	public void ao_adicionar_uma_resposta_a_lista_de_respostas_deve_ter_mais_uma_resposta() {
		Resposta resposta = new Resposta("Fazendo o código", topico, autor);
		
		List<Resposta> respostas = new ArrayList<Resposta>();
		respostas.add(resposta);
		
		// Quando
		// topico.addResposta(resposta);
		// topico.addResposta(resposta1);

		// Então
		assertEquals(respostas, topico.getRespostas());
	}
	
	@Test
	public void o_estado_deve_ser_fechado_quando_fechar_um_topico() {
		// Quando
		topico.fechar();

		// Então
		assertEquals(StatusTopico.FECHADO, topico.getStatus()); 
	}

}
