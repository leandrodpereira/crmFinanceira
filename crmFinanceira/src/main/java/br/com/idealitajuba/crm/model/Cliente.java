package br.com.idealitajuba.crm.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe que modela um cliente ativo ou inativo ou potencial cliente.
 * @author Leandro Duarte
 *
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;
	private String cpf;
	private String rg;
	private Date dataNascimento;	
	private BigDecimal renda;
	private String telefone;
	private String celular;
	private String email;
	private Long numeroBeneficio;
	private String observacoes;
	private Long idade = 0L;

	private SexoEnum sexo;
	private FontePagadoraEnum fontePagadora;
	private TipoBeneficio tipoBeneficio;

	@NotEmpty
	@Size(max = 11)
	@Column(length = 11, nullable = false, unique = true)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Size(max = 11)
	@Column(length = 11, nullable = true)
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg.toUpperCase();
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotNull
	@DecimalMin("1")
	@DecimalMax("1000000")
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	
	@Size(max = 13)
	@Column(length = 13, nullable = false)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	@Size(max = 14)
	@Column(length = 14, nullable = false)
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	
	@NotNull	
	@Column(length = 10, nullable = false)
	public Long getNumeroBeneficio() {
		return numeroBeneficio;
	}
	
	public void setNumeroBeneficio(Long numeroBeneficio) {
		this.numeroBeneficio = numeroBeneficio;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public FontePagadoraEnum getFontePagadora() {
		return fontePagadora;
	}

	public void setFontePagadora(FontePagadoraEnum fontePagadora) {
		this.fontePagadora = fontePagadora;
	}
	
	@NotNull
	@ManyToOne (optional = false)
	@JoinColumn(name = "tipo_beneficio_id", nullable = false)
	public TipoBeneficio getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

	@Size(max = 60)
	@Column(length = 60, nullable =	true)
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Transient	
	public Long getIdade() {	
		if(this.idade != null) {
			this.idade  = (Calendar.getInstance().getTimeInMillis() - this.getDataNascimento().getTime()) + 3600000;
			return (idade/31536000000L);
		}
		return idade;				
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}	

}
