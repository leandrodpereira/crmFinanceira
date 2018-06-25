package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroTipoBeneficio;
import br.com.idealitajuba.crm.business.CadastroTipoContatoStatus;
import br.com.idealitajuba.crm.model.TipoBeneficio;
import br.com.idealitajuba.crm.model.TipoContatoStatus;

@Named
@ViewScoped
public class CadastroTipoContatoStatusMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroTipoContatoStatus ctcs;
	
	private TipoContatoStatus tcs =  new TipoContatoStatus();
	
	public void preCadastro() {
		if(this.tcs == null) this.tcs = new TipoContatoStatus();
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.ctcs.salvar(this.tcs);
			this.tcs = new TipoContatoStatus();
			context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}

	public TipoContatoStatus getTcs() {
		return tcs;
	}

	public void setTcs(TipoContatoStatus tcs) {
		this.tcs = tcs;
	}	

}
