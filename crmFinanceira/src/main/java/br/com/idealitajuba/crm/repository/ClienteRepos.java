package br.com.idealitajuba.crm.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.Contato;

/**
 * Classe com pricipais métodos de acesso a dados.
 * 
 * @author Leandro Duarte
 *
 */
public class ClienteRepos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@Inject
	public ClienteRepos(EntityManager em) {
		this.em = em;
	}

	public void guardar(Cliente c) {
		this.em.merge(c);
	}

	public void remover(Cliente c) {
		this.em.remove(c);
	}

	public Cliente porId(Long id) {
		return this.em.find(Cliente.class, id);
	}

	public List<Cliente> todos() {
		TypedQuery<Cliente> query = this.em.createQuery("from Cliente", Cliente.class);
		return query.getResultList();
	}

	/**
	 * Método que verfica se existem ocorrências de Contatos associadas a um Cliente.
	 * Útil para verificar antes de exlcuir um tipo. *
	 * 
	 * @param id
	 * @return true se a lista estiver vazia.
	 * @author Leandro Duarte
	 */
	public boolean verificaCliente(Long id) {
		TypedQuery<Contato> query = this.em.createQuery("from Contato c where c.cliente.id=" + id, Contato.class);
		return query.getResultList().isEmpty();
	}

}
