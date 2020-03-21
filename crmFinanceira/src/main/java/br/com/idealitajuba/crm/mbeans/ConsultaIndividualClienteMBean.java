package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroContato;
import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.repository.ContatoRepos;

@Named
@ViewScoped
public class ConsultaIndividualClienteMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContatoRepos contatoRepos;
	
	@Inject
	private CadastroContato cadastroContato;
	
	private Cliente cliente;
	private Contato contatoSelecionado;
	
	private List<Contato> contatos = new ArrayList<Contato>();
	
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastroContato.excluir(contatoSelecionado);
			this.carregaContatos();
			context.addMessage(null, new FacesMessage("Contato excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void carregaContatos() {
		this.contatos = contatoRepos.porCliente(cliente);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}
	
		
}
