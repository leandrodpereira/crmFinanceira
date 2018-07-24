package br.com.idealitajuba.crm.repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.idealitajuba.crm.model.Agendamento;
import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.Contato;

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
	 * Método que retorna os contatos feitos a um cliente
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
	 * Método que retorna os contatos feitos num intervalo de datas
	 * @param dataInicio
	 * @param dataFim
	 * @return lista de Contatos
	 * @author Leandro Duarte
	 */
	public List<Contato> porData(Date dataInicio, Date dataFim) {
		TypedQuery<Contato> query = this.em.createQuery("from Contato c where "
				+ "date_format(c.dataHoraContato,'%d/%m/%Y') between '" + new SimpleDateFormat("dd/MM/yyyy").format(dataInicio)
				+"' and '"+ new SimpleDateFormat("dd/MM/yyyy").format(dataFim)+"'",
				Contato.class);
		return query.getResultList();
	}
	
	/**
	 * Método que verfica se existem agendamentos associados a um Contato. Útil para
	 * verificar antes de exlcuir .
	 * 
	 * @param id
	 * @return true se a lista estiver vazia.
	 * @author Leandro Duarte
	 */
	public boolean verificaAgendamento(Long id) {
		TypedQuery<Agendamento> query = this.em.createQuery("from Agendamento a where a.contato.id=" + id,
				Agendamento.class);
		return query.getResultList().isEmpty();
	}	
	

}
