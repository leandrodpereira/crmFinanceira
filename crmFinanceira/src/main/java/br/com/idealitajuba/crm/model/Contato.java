package br.com.idealitajuba.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Classe que modela o contato feito pelo usuario ao cliente.
 * 
 * @author Leandro Duarte
 *
 */
@Entity
@Table(name = "contato")
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataHoraContato;
	private String observacoes;
	private Date dataAgendamento;
	private Date horaAgendamento;
	private boolean concluido;

	private TipoContatoStatus status;

	private Usuario usuario;
	private Cliente cliente;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_contato", nullable = false)
	public Date getDataHoraContato() {
		return dataHoraContato;
	}

	public void setDataHoraContato(Date dataHoraContato) {
		this.dataHoraContato = dataHoraContato;
	}

	@Column(nullable = true)
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes.toUpperCase();
	}

	@ManyToOne
	@JoinColumn(name = "tipo_contato_status_id")
	public TipoContatoStatus getStatus() {
		return status;
	}

	public void setStatus(TipoContatoStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_agendamento", nullable = true)
	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_agendamento", nullable = true)
	public Date getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(Date horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}

	@NotNull
	@Column(nullable = false, columnDefinition = "boolean default false")
	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
