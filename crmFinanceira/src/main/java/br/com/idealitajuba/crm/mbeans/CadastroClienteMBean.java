package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroCliente;
import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.FontePagadoraEnum;
import br.com.idealitajuba.crm.model.SexoEnum;
import br.com.idealitajuba.crm.model.TipoBeneficio;
import br.com.idealitajuba.crm.repository.TipoBeneficioRepos;

@Named
@ViewScoped
public class CadastroClienteMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroCliente cc;
	
	@Inject
	private TipoBeneficioRepos tbr;
	
	private Cliente c =  new Cliente();
	
	private SexoEnum[] sexo;
	private FontePagadoraEnum[] fonte;
	private List<TipoBeneficio> tipos;
	
	public void preCadastro() {
		if(this.c == null) this.c = new Cliente();
		this.tipos = tbr.todos();
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cc.salvar(this.c);
			this.c = new Cliente();
			context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}

	public Cliente getC() {
		return c;
	}

	public void setC(Cliente c) {
		this.c = c;	}

	public SexoEnum[] getSexo() {
		return SexoEnum.values();
	}

	public FontePagadoraEnum[] getFonte() {
		return FontePagadoraEnum.values();
	}

	public List<TipoBeneficio> getTipos() {
		return this.tipos;
	}	
	
}
