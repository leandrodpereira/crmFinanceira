package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.CadastroCliente;
import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.FontePagadoraEnum;
import br.com.idealitajuba.crm.model.SexoEnum;
import br.com.idealitajuba.crm.model.TipoBeneficio;
import br.com.idealitajuba.crm.repository.ClienteRepos;
import br.com.idealitajuba.crm.repository.TipoBeneficioRepos;

@Named
@ViewScoped
public class CadastroClienteMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCliente cc;

	@Inject
	private TipoBeneficioRepos tbr;	
	
	@Inject
	private ClienteRepos cr;

	private Cliente c = new Cliente();
	
	private SexoEnum[] sexo;
	private FontePagadoraEnum[] fonte;
	private List<TipoBeneficio> tipos;
	private String msgCliente;

	public void preCadastro() {
		if (this.c == null) {
			this.c = new Cliente();	
		}
		this.isCliente();		
		this.setTipos(tbr.todos());
	}

	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg;
		try {
			this.cc.salvar(this.c);
			this.c = new Cliente();
			context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
		} catch (Exception e) {
			String clienteDuplicado = cr.porCpf(c.getCpf()).getNome();
			if (!clienteDuplicado.equals("")) {
				msg = new FacesMessage("Erro ao realizar cadastro, CPF "
						+ "já cadastrado para o (a) cliente "+clienteDuplicado+".");
			}else {
				msg = new FacesMessage(" Um erro ocorreu, transação não realizada.");
			}			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}
	
	public void isCliente() {
		this.msgCliente = this.c.isAtivo()?" é nosso cliente!":" ainda não é nosso cliente. :(";
	}

	public Cliente getC() {
		return c;
	}

	public void setC(Cliente c) {
		this.c = c;
	}

	public SexoEnum[] getSexo() {
		return SexoEnum.values();
	}

	public FontePagadoraEnum[] getFonte() {
		return FontePagadoraEnum.values();
	}

	public List<TipoBeneficio> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoBeneficio> tipos) {
		this.tipos = tipos;
	}

	public String getMsgCliente() {
		return msgCliente;
	}

	public void setMsgCliente(String msgCliente) {
		this.msgCliente = msgCliente;
	}
		

}
