package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroTipoBeneficio;
import br.com.idealitajuba.crm.model.TipoBeneficio;

@Named
@ViewScoped
public class CadastroTipoBeneficioMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroTipoBeneficio ctb;
	
	private TipoBeneficio tb =  new TipoBeneficio();
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.ctb.salvar(this.tb);
			this.tb = new TipoBeneficio();
			context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}

	public TipoBeneficio getTb() {
		return tb;
	}

	public void setTb(TipoBeneficio tb) {
		this.tb = tb;
	}

}
