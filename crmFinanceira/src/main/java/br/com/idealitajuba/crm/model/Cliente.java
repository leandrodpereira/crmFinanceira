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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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
	private String telefoneRecados;
	private String celular;
	private String email;
	private String matricula;
	private String observacoes;
	private Long idade = 0L;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private String cep;
	private String cidade;
	private String estado;
	private String banco;
	private String tipoConta;
	private String numeroAgencia;
	private String numeroConta;
	private String senha;	
	private boolean emprestimoPessoal;

	private SexoEnum sexo;
	private FontePagadoraEnum fontePagadora;
	private TipoBeneficio tipoBeneficio;
		
	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

	@NotEmpty	
	@Size(max = 14)
	@Column(length = 14, nullable = false, unique = true)
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
	
	
	@Column(name = "matricula")
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	
	@Enumerated(EnumType.STRING)
	@Column(name = "fonte_pagadora")
	public FontePagadoraEnum getFontePagadora() {
		return fontePagadora;
	}

	public void setFontePagadora(FontePagadoraEnum fontePagadora) {
		this.fontePagadora = fontePagadora;
	}
	

	@ManyToOne (optional = true)
	@JoinColumn(name = "tipo_beneficio_id")
	public TipoBeneficio getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

	@Column(nullable =	true)
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes.toUpperCase();
	}
	
	@Transient
	public boolean isFontePagadoraINSS() {
		if (this.getFontePagadora() != null)
			return this.getFontePagadora().equals(FontePagadoraEnum.INSS);
		return false;		
	}
	
	@Transient
	public boolean isFontePagadoraFGTS() {
		if (this.getFontePagadora() != null)
			return this.getFontePagadora().equals(FontePagadoraEnum.FGTS);
		return false;		
	}
	
		@Transient	
	public Long getIdade() {	
		if(this.idade != null && this.getDataNascimento() != null) {
			this.idade  = (Calendar.getInstance().getTimeInMillis() - this.getDataNascimento().getTime()) + 3600000;
			return (idade/31536000000L);
		}
		return idade;				
	}
	
	@Transient
	public boolean isIdadeAvancada() {
		return this.getIdade() >= 70;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	@Size(max = 60)
	@Column
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro.toUpperCase();
	}

	@Size(max = 4)
	@Column
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Size(max = 60)
	@Column
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
	}

	@Size(max = 60)
	@Column
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento.toUpperCase();
	}

	@Size(max = 9)
	@Column
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Size(max = 60)
	@Column
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade.toUpperCase();
	}

	@Size(max = 60)
	@Column
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "telefone_recados", nullable = false)	
	public String getTelefoneRecados() {
		return telefoneRecados;
	}

	public void setTelefoneRecados(String telefoneRecados) {
		this.telefoneRecados = telefoneRecados;
	}

	@Column	
	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco.toUpperCase();
	}

	@Column(name = "tipo_conta", nullable = true)	
	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	@Size(max = 10)
	@Column(name = "numero_agencia", nullable = true)	
	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	@Size(max = 12)
	@Column(name = "numero_conta", nullable = true)	
	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	@Column(name = "senha", nullable = true)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(nullable =	false, name = "emprestimo_pessoal", columnDefinition = "boolean default true")
	public boolean isEmprestimoPessoal() {
		return emprestimoPessoal;
	}

	public void setEmprestimoPessoal(boolean emprestimoPessoal) {
		this.emprestimoPessoal = emprestimoPessoal;
	}		

}
