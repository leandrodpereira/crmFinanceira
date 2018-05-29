package br.com.idealitajuba.crm.model;

import java.math.BigDecimal;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {

	private String cpf;
	private String rg;
	private Date dataNascimento;
	private BigDecimal renda;
	private String telefone;
	private String celular;
	private String email;

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
		this.rg = rg;
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
	@DecimalMin("0")
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	@NotEmpty
	@Size(max = 13)
	@Column(length = 13, nullable = false)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@NotEmpty
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
		this.email = email;
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

	@ManyToOne
	@JoinColumn(name = "tipo_beneficio_id")
	public TipoBeneficio getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

}
