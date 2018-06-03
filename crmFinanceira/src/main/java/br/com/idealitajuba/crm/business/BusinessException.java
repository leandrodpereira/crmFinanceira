package br.com.idealitajuba.crm.business;

/**
 * Classe que constroi as exceções das RN.
 * @author Leandro Duarte
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);		
	}
	
	
}
