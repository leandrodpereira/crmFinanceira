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

	public Cliente guardar(Cliente c) {
		return this.em.merge(c);
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
	 * Método que verfica se existem ocorrências de Contatos associadas a um
	 * Cliente. Útil para verificar antes de exlcuir um tipo. *
	 * 
	 * @param id
	 * @return true se a lista estiver vazia.
	 * @author Leandro Duarte
	 */
	public boolean verificaCliente(Long id) {
		TypedQuery<Contato> query = this.em.createQuery("from Contato c where c.cliente.id=" + id, Contato.class);
		return query.getResultList().isEmpty();
	}

	/**
	 * Busca por CPF
	 * 
	 * @param cpf
	 * @return
	 */
	public Cliente porCpf(String cpf) {
		try {
			return this.em.createQuery("from Cliente c where c.cpf = :cpf", Cliente.class).setParameter("cpf", cpf).
					getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Busca por cidade
	 * 
	 * @param cidade
	 * @return
	 */
	public List<Cliente> porCidade(String cidade) {
		TypedQuery<Cliente> query = this.em.createQuery("from Cliente c where upper(c.cidade) like upper(:cidade)",
				Cliente.class);
		query.setParameter("cidade", "%" + cidade + "%");
		return query.getResultList();
	}

	/**
	 * Busca por partes do nome
	 * 
	 * @param nome
	 * @return
	 */
	public List<Cliente> porNome(String nome) {
		TypedQuery<Cliente> query = this.em.createQuery("from Cliente c where upper(c.nome) like upper(:nome)",
				Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

	/**
	 * Método usado para autocompletar as cidades já existentes
	 * 
	 * @param cidade
	 * @return
	 */
	public List<String> cidades(String cidade) {
		TypedQuery<String> query = this.em.createQuery(
				"select distinct c.cidade from Cliente c where upper(c.cidade) like upper(:cidade)", String.class);
		query.setParameter("cidade", "%" + cidade + "%");
		return query.getResultList();
	}
	
	/**
	 * Método usado para autocompletar os bancos já existentes
	 * 
	 * @param banco
	 * @return
	 */
	public List<String> bancos(String banco) {
		TypedQuery<String> query = this.em.createQuery(
				"select distinct c.banco from Cliente c where upper(c.banco) like upper(:banco)", String.class);
		query.setParameter("banco", "%" + banco + "%");
		return query.getResultList();
	}

}
