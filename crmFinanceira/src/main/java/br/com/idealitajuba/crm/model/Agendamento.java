package br.com.idealitajuba.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 * Classe que modela um agendamento proveninete de um contato.
 * @author Leandro Duarte
 *
 */
@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dataAgendamento;
	private Date horaAgendamento;
	private boolean concluido;
	
	private Contato contato;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_agendamento", nullable = false)
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_agendamento", nullable = false)
	public Date getHoraAgendamento() {
		return horaAgendamento;
	}
	public void setHoraAgendamento(Date horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}	

	
	@NotNull
	@Column(nullable =	false, columnDefinition = "boolean default false")
	public boolean isConcluido() {
		return concluido;
	}
	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	
	@OneToOne
	@JoinColumn(name = "contato_id")
	public Contato getContato() {
		return contato;
	}
	
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
