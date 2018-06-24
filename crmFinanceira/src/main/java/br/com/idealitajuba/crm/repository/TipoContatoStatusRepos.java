package br.com.idealitajuba.crm.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.model.TipoContatoStatus;

/**
 * Classe com pricipais métodos de acesso a dados.
 * 
 * @author Leandro Duarte
 *
 */
public class TipoContatoStatusRepos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@Inject
	public TipoContatoStatusRepos(EntityManager em) {
		this.em = em;
	}

	public void guardar(TipoContatoStatus tb) {
		this.em.merge(tb);
	}

	public void remover(TipoContatoStatus tb) {
		this.em.remove(tb);
	}

	public TipoContatoStatus porId(Long id) {
		return this.em.find(TipoContatoStatus.class, id);
	}

	public List<TipoContatoStatus> todos() {
		TypedQuery<TipoContatoStatus> query = this.em.createQuery("from TipoContatoStatus", TipoContatoStatus.class);
		return query.getResultList();
	}

	/**
	 * Método que verfica se existem ocorrências de Contatos associadas a um Tipo.
	 * Útil para verificar antes de exlcuir um tipo. *
	 * 
	 * @param id
	 * @return true se a lista estiver vazia.
	 * @author Leandro Duarte
	 */
	public boolean verificaTipoContatoStatus(Long id) {
		TypedQuery<Contato> query = this.em.createQuery("from Contato c where c.tipoContatoStatus.id=" + id, Contato.class);
		return query.getResultList().isEmpty();
	}

}
