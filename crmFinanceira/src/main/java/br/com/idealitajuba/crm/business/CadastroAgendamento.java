package br.com.idealitajuba.crm.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.idealitajuba.crm.model.Agendamento;
import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.repository.AgendamentoRepos;
import br.com.idealitajuba.crm.util.Transactional;

/**
 * Classe que implementa as regras de negócio.
 * 
 * @author Leandro Duarte
 *
 */
public class CadastroAgendamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgendamentoRepos ar;

	@Transactional
	public void salvar(Agendamento a) throws BusinessException {
		this.ar.guardar(a);

	}

	/**
	 * RN01 - Não é possível excluir Agendamento que não foi concluído.	  
	 * @param a
	 * @throws BusinessException
	 */
	@Transactional
	public void excluir(Agendamento a) throws BusinessException {
		a = this.ar.porId(a.getId());
		if (!a.isConcluido())
			throw new BusinessException("Não é possível excluir Agendamento pendente.");
		this.ar.remover(a);
	}
}
