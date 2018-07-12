package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroUsuario;
import br.com.idealitajuba.crm.model.Usuario;
import br.com.idealitajuba.crm.repository.UsuarioRepos;

@Named
@ViewScoped
public class ConsultaUsuarioMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepos ur;
	@Inject
	private CadastroUsuario cu;

	private Usuario usuarioSelecionado;
	private List<Usuario> usuarios;

	public void mostrarTodos() {
		this.usuarios = ur.todos();
	}


	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cu.excluir(usuarioSelecionado);
			this.mostrarTodos();
			context.addMessage(null, new FacesMessage("Usuário excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}	
	}


	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
