package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroTipoBeneficio;
import br.com.idealitajuba.crm.model.TipoBeneficio;
import br.com.idealitajuba.crm.repository.TipoBeneficioRepos;

@Named
@ViewScoped
public class ConsultaTipoBeneficioMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoBeneficioRepos tbr;
	@Inject
	private CadastroTipoBeneficio ctb;

	private List<TipoBeneficio> tipos;

	private TipoBeneficio tbSelecionado;

	public void mostrarTodos() {
		this.tipos = tbr.todos();
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.ctb.excluir(tbSelecionado);
			this.mostrarTodos();
			context.addMessage(null, new FacesMessage("Tipo excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<TipoBeneficio> getTipos() {
		return tipos;
	}

	public TipoBeneficio getTbSelecionado() {
		return tbSelecionado;
	}

	public void setTbSelecionado(TipoBeneficio tbSelecionado) {
		this.tbSelecionado = tbSelecionado;
	}

}
