package br.com.alura.forum.service;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.alura.forum.dao.PerfilDao;
import br.com.alura.forum.dao.UsuarioDao;
import br.com.alura.forum.model.Perfil;
import br.com.alura.forum.model.Usuario;

public class CadastroUsuarioServiceTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_lancar_excecao_quando_ja_existe_o_usuario() {
		UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
		Usuario usuario = new Usuario("Newton", "newton@caelum.com.br", "abc123");
		Mockito.when(usuarioDao.buscarPorEmail("newton@caelum.com.br")).thenReturn(usuario);
				
		CadastroUsuarioService service = new CadastroUsuarioService(usuarioDao, null, null);
		service.cadastrarNovoUsuario(usuario);
	}
	
	@Test
	public void cadastra_novo_usuario() {
		UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
		Usuario usuario = new Usuario("Newton", "newton@caelum.com.br", "abc123");
		Mockito.when(usuarioDao.buscarPorEmail("newton@caelum.com.br")).thenReturn(null);

		PerfilDao perfilDao = Mockito.mock(PerfilDao.class);
		Perfil perfil = new Perfil("aluno");
		Mockito.when(perfilDao.buscarPorNome("ROLE_ALUNO")).thenReturn(perfil);
		
		Md5Service md5 = Mockito.mock(Md5Service.class);
		Mockito.when(md5.encode("123")).thenReturn("123");
				
		CadastroUsuarioService service = new CadastroUsuarioService(usuarioDao, perfilDao, md5);
		service.cadastrarNovoUsuario(usuario);
		
		Mockito.verify(usuarioDao, Mockito.times(1)).salvar(usuario);
		
	}

}
