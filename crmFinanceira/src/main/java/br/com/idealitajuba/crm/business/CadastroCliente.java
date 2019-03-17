package br.com.idealitajuba.crm.business;

import java.io.Serializable;

import javax.inject.Inject;

import org.jboss.weld.ejb.spi.BusinessInterfaceDescriptor;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.repository.ClienteRepos;
import br.com.idealitajuba.crm.util.Transactional;

/**
 * Classe que implementa as regras de negócio.
 * 
 * @author Leandro Duarte
 *
 */
public class CadastroCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepos cr;

	/**
	 * Metodo que valida CPF
	 * @param cpf
	 * @return
	 */
	public static boolean valida(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		if (cpfValidator.invalidMessagesFor(cpf).isEmpty())
			return true;
		else 
			return false;
	}
	/**
	 * RN02 - Não é possível cadastrar Clientes que possuem o mesmo CPF.
	 * RN03 - Não é possível cadastrar clientes com CPF inválido.
	 * @param c
	 * @throws BusinessException
	 */
	@Transactional
	public void salvar(Cliente c) throws BusinessException {	
		
		if (!valida(c.getCpf()))
			throw new BusinessException("CPF inválido.");		
		
		if (cr.porCpf(c.getCpf()) != null)
			throw new BusinessException("Erro ao realizar cadastro, CPF " + "já cadastrado para o (a) cliente "
						+ c.getNome() + ".");	
			
		this.cr.guardar(c);
	}

	/**
	 * RN01 - Não é possível excluir Cliente que possui Contato associado.  
	 * @param c
	 * @throws BusinessException
	 */
	@Transactional
	public void excluir(Cliente c) throws BusinessException {
		c = this.cr.porId(c.getId());
		if (!this.cr.verificaCliente(c.getId()))
			throw new BusinessException("Não é possível excluir " + c.getNome()
					+ ", pois possui contato associado. Caso o Cliente não esteja mais ativo, desative-o pela opção de Editar");
		this.cr.remover(c);
	}

}
