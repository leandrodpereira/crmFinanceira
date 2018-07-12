package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroUsuario;
import br.com.idealitajuba.crm.model.Usuario;

@Named
@ViewScoped
public class CadastroUsuarioMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuario cu;

	private Usuario u = new Usuario();
	
	public void preCadastro() {
		if (this.u == null) {
			this.u = new Usuario();	
		}
		
	}

	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cu.salvar(this.u);
			this.u = new Usuario();
			context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

}
