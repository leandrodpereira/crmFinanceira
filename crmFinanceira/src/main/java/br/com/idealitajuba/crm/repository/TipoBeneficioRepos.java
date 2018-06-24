package br.com.idealitajuba.crm.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.TipoBeneficio;

/**
 * Classe com pricipais métodos de acesso a dados.
 * @author Leandro Duarte
 *
 */
public class TipoBeneficioRepos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@Inject
	public TipoBeneficioRepos(EntityManager em) {
		this.em = em;
	}

	public void guardar(TipoBeneficio tb) {
		this.em.merge(tb);
	}

	public void remover(TipoBeneficio tb) {
		this.em.remove(tb);
	}

	public TipoBeneficio porId(Long id) {
		return this.em.find(TipoBeneficio.class, id);
	}

	public List<TipoBeneficio> todos() {
		TypedQuery<TipoBeneficio> query	= 
				this.em.createQuery("from TipoBeneficio", TipoBeneficio.class);	
		return query.getResultList();
	}
	
	/**
	 * Método que verfica se existem ocorrências de cliente associadas a 
	 * um Tipo de Benefício. Útil para verificar antes de exlcuir um tipo.
	 * @param id
	 * @return true se a lista estiver vazia.
	 * @author Leandro Duarte
	 */
	public boolean verificaClienteTipoBeneficio(Long id) {
		TypedQuery<Cliente> query =
				this.em.createQuery("from Cliente c where c.tipoBeneficio.id="+id,Cliente.class);
		return query.getResultList().isEmpty();
	}

}
