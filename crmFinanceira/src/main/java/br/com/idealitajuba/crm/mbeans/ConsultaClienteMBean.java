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

	private String cpf, nome, cidade;
	private Cliente clienteSelecionado;
	private List<Cliente> clientes = new ArrayList<>();

	public void mostrarPorCpf() {
		if(cr.porCpf(this.cpf) != null)
			this.clientes.add(cr.porCpf(this.cpf));
	}
	
	public void mostrarPorNome() {
		this.clientes = cr.porNome(this.nome);
	}

	public void mostrarTodos() {
		this.clientes = cr.todos();
	}

	public void mostrarPorCidade() {
		this.clientes = cr.porCidade(this.cidade);
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cc.excluir(clienteSelecionado);
			this.mostrarTodos();
			context.addMessage(null, new FacesMessage("Cliente excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
