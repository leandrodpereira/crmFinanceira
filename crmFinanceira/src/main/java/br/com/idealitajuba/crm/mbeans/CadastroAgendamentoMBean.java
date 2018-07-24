package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroAgendamento;
import br.com.idealitajuba.crm.model.Agendamento;
import br.com.idealitajuba.crm.model.Contato;

@Named
@ViewScoped
public class CadastroAgendamentoMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAgendamento ca;
	@Inject
	private Contato c;

	private Agendamento a = new Agendamento();
	
	public void preCadastro() {
		if (this.a == null) {
			this.a = new Agendamento();	
		}
		this.a.setContato(c);
	}

	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.ca.salvar(this.a);
			this.a = new Agendamento();
			context.addMessage(null, new FacesMessage("Agendamento para o cliente "
			+this.a.getContato().getCliente().getNome()+" realizado com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}

	public Agendamento getA() {
		return a;
	}

	public void setA(Agendamento a) {
		this.a = a;
	}

	

}
