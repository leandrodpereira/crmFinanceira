package br.com.idealitajuba.crm.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.idealitajuba.crm.mbeans.LoginMBean;
import br.com.idealitajuba.crm.model.Usuario;
import br.com.idealitajuba.crm.repository.UsuarioRepos;
import br.com.idealitajuba.crm.util.jpa.Transactional;

/**
 * Classe que implementa as regras de negócio.
 * 
 * @author Leandro Duarte
 *
 */
public class CadastroUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepos ur;

	@Inject
	private LoginMBean l;

	/**
	 * RN01 - Apenas o Administrador do sistema pode realizar esta operação.
	 * RN02 - Não é possível excluir Cliente que possui Contato associado.  
	 * @param c
	 * @throws BusinessException
	 */
	@Transactional
	public void excluir(Usuario u) throws BusinessException {
		u = this.ur.porId(u.getId());
		if (!this.l.getLogin().equals("admin"))
			throw new BusinessException("Apenas o Administrador do sistema pode realizar esta operação.");
		if (!this.ur.verificaUsuario(u.getId()))
			throw new BusinessException("Não é possível excluir Usuário que possui Contato associado.");
		this.ur.remover(u);
	}

	/**
	 * 
	 * @param u
	 * @throws BusinessException
	 */
	@Transactional
	public void salvar(Usuario u) throws BusinessException {
		if (!this.l.getLogin().equals("admin"))
			throw new BusinessException("Apenas o Administrador do sistema pode realizar esta operação.");
		this.ur.guardar(u);
	}
}
