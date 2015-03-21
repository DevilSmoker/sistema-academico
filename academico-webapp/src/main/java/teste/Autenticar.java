package teste;

import br.com.anderson.academico.dao.impl.UsuarioDaoImpl;
import br.com.anderson.academico.modelo.Usuario;

public class Autenticar {
	public static void main(String[] args) {
		UsuarioDaoImpl dao = new UsuarioDaoImpl();
		Usuario usuario = dao.autenticar("anderson", "root");
		
		System.out.println(usuario.getNome());
	}

}
