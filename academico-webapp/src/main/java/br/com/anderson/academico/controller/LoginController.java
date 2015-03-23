package br.com.anderson.academico.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.anderson.academico.dao.impl.UsuarioDaoImpl;
import br.com.anderson.academico.modelo.Usuario;

@ManagedBean
@SessionScoped
public class LoginController {

	@NotEmpty
	private String login;
	@NotEmpty
	private String senha;
	private Usuario usuario;

	public String logar(){
		UsuarioDaoImpl dao = new UsuarioDaoImpl();
		FacesContext context = FacesContext.getCurrentInstance();
		
		usuario = dao.autenticar(login, senha);
		
		if (usuario == null) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha inválidos!", null));
			return "/login";
		} else {
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("usuario", usuario);
			return "/secure/index?faces-redirect=true";
		}
	}
	
	public String deslogar(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
