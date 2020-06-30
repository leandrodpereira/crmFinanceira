package br.com.idealitajuba.crm.model;

/**
 * Constantes com os tipos de fontes pagadoras.
 * 
 * @author Leandro Duarte
 * 
 */
public enum FontePagadoraEnum {
	// To-Do: Converter este Enum em Entidade.
	SIAPE("Siape"), INSS("Inss"), SEPLAG("Seplag"), EXERCITO("Exército"), MARINHA("Marinha"),
	AERONAUTICA("Aeronáutica"), POLICIA_MILITAR("Polícia Militar"), BOMBEIROS("Bombeiros");

	private String descricao;

	private FontePagadoraEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao.toUpperCase();
	}

}
