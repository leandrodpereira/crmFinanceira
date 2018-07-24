package br.com.idealitajuba.crm.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.repository.ContatoRepos;
import br.com.idealitajuba.crm.util.Transactional;

/**
 * Classe que implementa as regras de negócio.
 * 
 * @author Leandro Duarte
 *
 */
public class CadastroContato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContatoRepos cr;

	@Transactional
	public void salvar(Contato c) throws BusinessException {
		this.cr.guardar(c);

	}

	/**
	 * RN01 - Não é possível excluir Contato que possui Agendamento associado.	  
	 * @param c
	 * @throws BusinessException
	 */
	@Transactional
	public void excluir(Contato c) throws BusinessException {
		c = this.cr.porId(c.getId());
		if (!this.cr.verificaAgendamento((c.getId())))
			throw new BusinessException("Não é possível excluir Contato que possui Agendamento associado.");
		this.cr.remover(c);
	}
}
