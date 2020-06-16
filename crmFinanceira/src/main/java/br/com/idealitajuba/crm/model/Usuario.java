package br.com.idealitajuba.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Classe que modela o usuario do sistema.
 * @author Leandro Duarte
 *
 */

@Entity
@Table(name="usuario")
public class Usuario extends Pessoa {

	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;	
	
	@NotEmpty
	@Size(max = 10)
	@Column(length = 10, nullable =	false, unique = true)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@NotEmpty
	@Size(min = 6, max = 10)
	@Column(nullable =	false)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
