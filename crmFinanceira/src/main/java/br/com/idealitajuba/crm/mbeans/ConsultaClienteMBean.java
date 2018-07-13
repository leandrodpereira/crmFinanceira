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
import br.com.idealitajuba.crm.repository.ClienteRepos;

@Named
@ViewScoped
public class ConsultaClienteMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepos cr;
	@Inject
	private CadastroCliente cc;

	private String cpf, nome;
	private Cliente clienteSelecionado;
	private List<Cliente> clientes;

	public void mostrarPorCpf() {
		this.clientes = cr.porCpfLista(this.cpf);
	}

	public void mostrarPorNome() {
		this.clientes = cr.porNome(this.nome);
	}

	public String excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cc.excluir(clienteSelecionado);
			context.addMessage(null, new FacesMessage("Cliente excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		return "/ConsultaCliente?faces-redirect=true";
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
