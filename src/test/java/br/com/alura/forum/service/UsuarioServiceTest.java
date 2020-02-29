package br.com.alura.forum.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.alura.forum.dao.UsuarioDao;
import br.com.alura.forum.model.Usuario;

public class UsuarioServiceTest {

	UsuarioDao dao;
	UsuarioService service;
	
	@Before
	public void before() {
		dao = Mockito.mock(UsuarioDao.class);
		service = new UsuarioService(dao);
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void deve_lancar_excecao_ao_buscar_usuario_por_email_que_nao_existe_no_banco() {
		dao = Mockito.mock(UsuarioDao.class);
		service = new UsuarioService(dao);

		service.loadUserByUsername("newton@caelum.com.br");
	}

	@Test
	public void deve_retornar_o_usuario_do_email_passado_como_parametro() {
		dao = Mockito.mock(UsuarioDao.class);
		Usuario usuario = new Usuario("Newton", "newton@caelum.com.br", "abc123");
		Mockito.when(dao.buscarPorEmail("newton@caelum.com.br")).thenReturn(usuario);

		service = new UsuarioService(dao);

		UserDetails usuarioRetornado = service.loadUserByUsername("newton@caelum.com.br");

		Assert.assertEquals("newton@caelum.com.br", usuarioRetornado.getUsername());
	}

}
