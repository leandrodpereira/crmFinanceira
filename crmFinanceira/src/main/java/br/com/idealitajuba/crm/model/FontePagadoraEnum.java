package br.com.idealitajuba.crm.model;

public enum FontePagadoraEnum {
	
	SIAPE ("Siape"), INSS ("Inss");
	
	private String descricao;	

	private FontePagadoraEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
