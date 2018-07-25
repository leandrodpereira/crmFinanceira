package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.model.Usuario;
import br.com.idealitajuba.crm.repository.UsuarioRepos;

@Named
@SessionScoped
public class LoginMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;	
	private boolean logado;

	@Inject
	private UsuarioRepos ur;
	
	private Usuario usuario;

	/**
	 * Método para autenticar o usuario. *
	 */
	public String fazLogin() {
		
			FacesContext context = FacesContext.getCurrentInstance();

			this.usuario = ur.porLogin(this.login);
						

			if (this.usuario == null) {

				FacesMessage mensagem = new FacesMessage("Usuário não cadastrado, procure o administrador do sistema.");
				mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage(null, mensagem);

			} else if (usuario.getLogin().equals(this.login) && usuario.getSenha().equals(this.senha) && usuario.isAtivo()) {

				this.logado = true;
				return "/Home?faces-redirect=true";

			} else {

				FacesMessage mensagem = new FacesMessage("Usuário ou senha inválidos!");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, mensagem);
			}
				
		
		return null;

	}

	/**
	 * Método para Logout *
	 */
	public String fazLogout() {
		this.logado = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";

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

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

}
