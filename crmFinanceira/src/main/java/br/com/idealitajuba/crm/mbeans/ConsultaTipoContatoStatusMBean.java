package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroTipoContatoStatus;
import br.com.idealitajuba.crm.model.TipoContatoStatus;
import br.com.idealitajuba.crm.repository.TipoContatoStatusRepos;

@Named
@ViewScoped
public class ConsultaTipoContatoStatusMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoContatoStatusRepos tcsr;
	@Inject
	private CadastroTipoContatoStatus ctcs;

	private List<TipoContatoStatus> tipos;

	private TipoContatoStatus tcsSelecionado;

	public void mostrarTodos() {
		this.tipos = tcsr.todos();
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.ctcs.excluir(tcsSelecionado);
			this.mostrarTodos();
			context.addMessage(null, new FacesMessage("Tipo excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<TipoContatoStatus> getTipos() {
		return tipos;
	}

	
	public TipoContatoStatus getTcsSelecionado() {
		return tcsSelecionado;
	}

	public void setTcsSelecionado(TipoContatoStatus tbSelecionado) {
		this.tcsSelecionado = tbSelecionado;
	}


}
