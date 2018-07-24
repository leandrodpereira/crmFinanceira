package br.com.idealitajuba.crm.repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.idealitajuba.crm.model.Agendamento;

/**
 * Classe com pricipais métodos de acesso a dados.
 * 
 * @author Leandro Duarte
 *
 */
public class AgendamentoRepos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@Inject
	public AgendamentoRepos(EntityManager em) {
		this.em = em;
	}

	public void guardar(Agendamento a) {
		this.em.merge(a);
	}

	public void remover(Agendamento a) {
		this.em.remove(a);
	}

	public Agendamento porId(Long id) {
		return this.em.find(Agendamento.class, id);
	}

	public List<Agendamento> todos() {
		TypedQuery<Agendamento> query = this.em.createQuery("from Agendamento", Agendamento.class);
		return query.getResultList();
	}

	/**
	 * Método que retorna os agendamento feitos num intervalo de datas
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return lista de Agendamentos
	 * @author Leandro Duarte
	 */
	public List<Agendamento> porData(Date dataInicio, Date dataFim) {
		TypedQuery<Agendamento> query = this.em
				.createQuery("from Agendamento a where " + "date_format(a.dataAgendamento,'%d/%m/%Y') between '"
						+ new SimpleDateFormat("dd/MM/yyyy").format(dataInicio) + "' and '"
						+ new SimpleDateFormat("dd/MM/yyyy").format(dataFim) + "'", Agendamento.class);
		return query.getResultList();
	}

}
