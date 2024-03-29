package br.com.idealitajuba.crm.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.JDBCException;

import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.model.Usuario;

/**
 * Classe com pricipais métodos de acesso a dados.
 * 
 * @author Leandro Duarte
 *
 */
public class UsuarioRepos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@Inject
	public UsuarioRepos(EntityManager em) {
		this.em = em;
	}

	public void guardar(Usuario u) {
		this.em.merge(u);
	}

	public void remover(Usuario u) {
		this.em.remove(u);
	}

	public Usuario porId(Long id) {
		return this.em.find(Usuario.class, id);
	}

	public List<Usuario> todos() {
		TypedQuery<Usuario> query = this.em.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
	}
	
	/**
	 * Método que busca por Login
	 * @param login
	 * @return Usuario
	 */	
	public Usuario porLogin(String login) {
		try {
			Query query = this.em.createQuery("from Usuario u where u.login = :login");
			query.setParameter("login",  login );
			return (Usuario) query.getSingleResult();
		}catch (Exception e) {}
		
		return null;	 
	}

	/**
	 * Método que verfica se existem ocorrências de Contatos associadas a um
	 * Usuario. Útil para verificar antes de exlcuir um tipo. 	 * 
	 * @param id
	 * @return true se a lista estiver vazia.
	 * @author Leandro Duarte
	 */
	public boolean verificaUsuario(Long id) {
		TypedQuery<Contato> query = this.em.createQuery("from Contato c where c.usuario.id=" + id, Contato.class);
		return query.getResultList().isEmpty();
	}
	
	/**
	 * Método que verfica a unicidade do CPF
	 * @param cpf
	 * @return true se a lista estiver vazia
	 * @author Leandro Duarte
	 */	
	

}
