package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.Calendar;
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
import br.com.idealitajuba.crm.model.TipoContatoStatus;
import br.com.idealitajuba.crm.repository.TipoContatoStatusRepos;

@Named
@ViewScoped
public class CadastroContatoMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroContato cc;
	@Inject
	private LoginMBean login;
	@Inject
	private TipoContatoStatusRepos tcsr;

	private Contato con = new Contato();
	private Cliente cli = new Cliente();
	private List<TipoContatoStatus> tipoStatus;

	public void preCadastro() {
		if (this.con == null) {
			this.con = new Contato();
		}
		this.tipoStatus = tcsr.todos();
		if(con.getCliente() == null) this.con.setCliente(cli);
		this.con.setUsuario(login.getUsuario());
		this.con.setDataHoraContato(Calendar.getInstance().getTime());
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cc.salvar(this.con);
			context.addMessage(null, new FacesMessage("Contato registrado com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		return "";
	}

	public Contato getCon() {
		return con;
	}

	public void setCon(Contato con) {
		this.con = con;
	}

	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public List<TipoContatoStatus> getTipoStatus() {
		return tipoStatus;
	}

	public void setTipoStatus(List<TipoContatoStatus> tipoStatus) {
		this.tipoStatus = tipoStatus;
	}

}
