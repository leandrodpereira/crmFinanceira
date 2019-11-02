package br.com.idealitajuba.crm.model;

/**
 * Constantes com os tipos de fontes pagadoras.
 * @author Leandro Duarte
 *
 */
public enum FontePagadoraEnum {
	
	SIAPE ("Siape"), INSS ("Inss"), SEPLAG ("Seplag");
	
	private String descricao;	

	private FontePagadoraEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
