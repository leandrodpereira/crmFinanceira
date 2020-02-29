package br.com.idealitajuba.crm.repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.model.Usuario;

/**
 * Classe com pricipais métodos de acesso a dados.
 * 
 * @author Leandro Duarte
 *
 */
public class ContatoRepos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@Inject
	public ContatoRepos(EntityManager em) {
		this.em = em;
	}

	public void guardar(Contato c) {
		this.em.merge(c);
	}

	public void remover(Contato c) {
		this.em.remove(c);
	}

	public Contato porId(Long id) {
		return this.em.find(Contato.class, id);
	}

	public List<Contato> todos() {
		TypedQuery<Contato> query = this.em.createQuery("from Contato", Contato.class);
		return query.getResultList();
	}

	/**
	 * Método que retorna os contatos feitos a um cliente.
	 * 
	 * @param cliente
	 * @return lista de Contatos
	 * @author Leandro Duarte
	 */
	public List<Contato> porCliente(Cliente cliente) {
		TypedQuery<Contato> query = this.em.createQuery("from Contato c where c.cliente.id=" + cliente.getId(),
				Contato.class);
		return query.getResultList();
	}

	/**
	 * Método que retorna os contatos feitos num intervalo de datas.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return lista de Contatos
	 * @author Leandro Duarte
	 */
	public List<Contato> porData(Date dataInicio, Date dataFim) {
		TypedQuery<Contato> query = this.em.createQuery(
				"from Contato c where c.dataHoraContato between '"
						+new SimpleDateFormat("yyyy-MM-dd").format(dataInicio)+
						"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dataFim)+"'", Contato.class);
	
		return query.getResultList();
	}

	/**
	 * Método que mostra os contatos com agendamento pendentes por usuario no dia
	 * presente.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public List<Contato> porAgendamentoPendenteUsuario(Usuario u) {
		TypedQuery<Contato> query = this.em.createQuery(
				"from Contato c where " + "c.status.pendencia='1' and c.usuario.id=" + u.getId()
						+ " and date_format(c.dataAgendamento,'%d/%m/%Y') = '"
						+ new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()) + "'",
				Contato.class);
		return query.getResultList();
	}
	
	
	/**
	 * Método que mostra os contatos com agendamento pendentes no dia
	 * presente.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public List<Contato> porAgendamentoPendenteDia() {
		TypedQuery<Contato> query = this.em.createQuery(
				"from Contato c where " + "c.status.pendencia='1' and date_format(c.dataAgendamento,'%d/%m/%Y') = '"
						+ new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()) + "'",
				Contato.class);
		return query.getResultList();
	}
	

	/**
	 * Método que mostra os contatos com agendamento pendentes.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public List<Contato> porAgendamentoPendente() {
		TypedQuery<Contato> query = this.em.createQuery("from Contato c where " + "c.status.pendencia='1' order by c.dataAgendamento asc",
				Contato.class);
		return query.getResultList();
	}
}
