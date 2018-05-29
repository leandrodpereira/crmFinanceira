package br.com.idealitajuba.crm.model;

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
