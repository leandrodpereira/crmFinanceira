package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroContato;
import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.repository.ContatoRepos;

@Named
@ViewScoped
public class ConsultaContatoMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContatoRepos cr;
	@Inject
	private CadastroContato cc;
	@Inject
	private LoginMBean login;

	private Contato contatoSelecionado;
	private List<Contato> contatos;
	
	private Date dataInicio;
	private Date dataFim;

	/**
	 * Lista todos Contatos
	 */
	public void mostrarTodos() {
		this.setContatos(cr.todos());
	}
	
	public void mostrarPorIntervalo() {
		this.setContatos(cr.porData(dataInicio, dataFim));
	}
	
	public void mostrarAgendamentoPendentePorUsuario() {
		this.setContatos(cr.porAgendamentoPendenteUsuario(login.getUsuario()));
	}
	
	public void mostrarAgendamentoPendente() {
		this.setContatos(cr.porAgendamentoPendente());
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cc.excluir(contatoSelecionado);
			this.mostrarTodos();
			context.addMessage(null, new FacesMessage("Contato excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
