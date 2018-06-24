package br.com.idealitajuba.crm.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.idealitajuba.crm.model.TipoContatoStatus;
import br.com.idealitajuba.crm.repository.TipoContatoStatusRepos;
import br.com.idealitajuba.crm.util.Transactional;

/**
 * Classe que implementa as regras de negócio.
 * @author Leandro Duarte
 *
 */
public class CadastroTipoContatoStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoContatoStatusRepos tcsr;
	
	@Transactional
	public void salvar(TipoContatoStatus tcs) throws BusinessException{
		this.tcsr.guardar(tcs);
	}
	/**
	 * RN01 - Não é possível excluir Tipo que possui Contato associado.
	 * @param tcs
	 * @throws BusinessException
	 */
	@Transactional
	public void excluir(TipoContatoStatus tcs) throws BusinessException{
		tcs = this.tcsr.porId(tcs.getId());
		if (!this.tcsr.verificaTipoContatoStatus((tcs.getId())))
				throw new BusinessException("Não é possível excluir um Tipo que "
						+ "possui Contato associado.");
		this.tcsr.remover(tcs);
	}
}