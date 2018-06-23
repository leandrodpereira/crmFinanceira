package br.com.idealitajuba.crm.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.idealitajuba.crm.model.TipoBeneficio;
import br.com.idealitajuba.crm.repository.TipoBeneficioRepos;
import br.com.idealitajuba.crm.util.Transactional;

/**
 * Classe que implementa as regras de negócio.
 * @author Leandro Duarte
 *
 */
public class CadastroTipoBeneficio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoBeneficioRepos tbr;
	
	@Transactional
	public void salvar(TipoBeneficio tb) throws BusinessException{
		this.tbr.guardar(tb);
	}
	/**
	 * RN01 - Não é possível excluir Tipo que possui Cliente associado.
	 * @param tb
	 * @throws BusinessException
	 */
	@Transactional
	public void excluir(TipoBeneficio tb) throws BusinessException{
		tb = this.tbr.porId(tb.getId());
		if (!this.tbr.verificaClienteTipoBeneficio(tb.getId())) 
				throw new BusinessException("Não é possível excluir Tipo que "
						+ "possui Cliente associado.");
		this.tbr.remover(tb);
	}
}
