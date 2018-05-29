package br.com.idealitajuba.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe que modela os tipos de benefícios recebidos pelos clientes.
 * @author Leandro Duarte
 *
 */
@Entity
@Table(name = "tipo_beneficio")
public class TipoBeneficio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long codigo;
	private String descricao;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@NotNull	
	@Column(nullable = false)
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	@NotEmpty
	@Size(max = 40)
	@Column(length = 40, nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		TipoBeneficio other = (TipoBeneficio) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
