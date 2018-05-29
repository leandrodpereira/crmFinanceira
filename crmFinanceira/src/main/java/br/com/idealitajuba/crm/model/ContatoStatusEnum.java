package br.com.idealitajuba.crm.model;

/**
 * Constantes dos possíveis status que podem ocorrer em um atendimento.
 * @author Leandro Duarte
 *
 */
public enum ContatoStatusEnum {
	
	SEM_INTERESSE("Sem interesse"), NAO_ENCONTRADO("Não encontrado"), 
	INTERESSE_FUTURO("Interesse futuro");
	
	private String descricao;
	
	ContatoStatusEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	

}
