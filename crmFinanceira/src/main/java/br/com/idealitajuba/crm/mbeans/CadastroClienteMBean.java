package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.idealitajuba.crm.business.BusinessException;
import br.com.idealitajuba.crm.business.CadastroCliente;
import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.model.FontePagadoraEnum;
import br.com.idealitajuba.crm.model.SexoEnum;
import br.com.idealitajuba.crm.model.TipoBeneficio;
import br.com.idealitajuba.crm.repository.ClienteRepos;
import br.com.idealitajuba.crm.repository.TipoBeneficioRepos;
import br.com.idealitajuba.crm.util.Estado;

@Named
@ViewScoped
public class CadastroClienteMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCliente cc;

	@Inject
	private TipoBeneficioRepos tbr;

	@Inject
	private ClienteRepos cr;

	private Cliente c;

	private SexoEnum[] sexo;
	private List<TipoBeneficio> tipos;
	private Date hoje;
	private List<String> estados;

	FacesContext context;
	FacesMessage msg;

	private List<SelectItem> fontes;

	/*
	 * Método que agrupo as Fontes Pagadoras em um SelectItem.
	 */
	public void agrupaFontePagadora() {
		SelectItemGroup grupoGovFederal = new SelectItemGroup("Governo Federal");
		grupoGovFederal.setSelectItems(
				new SelectItem[] { new SelectItem(FontePagadoraEnum.INSS, FontePagadoraEnum.INSS.getDescricao()),
						new SelectItem(FontePagadoraEnum.SIAPE, FontePagadoraEnum.SIAPE.getDescricao()) });

		SelectItemGroup grupoForcarArmadas = new SelectItemGroup("Forças Armadas");
		grupoForcarArmadas.setSelectItems(new SelectItem[] {
				new SelectItem(FontePagadoraEnum.EXERCITO, FontePagadoraEnum.EXERCITO.getDescricao()),
				new SelectItem(FontePagadoraEnum.MARINHA, FontePagadoraEnum.MARINHA.getDescricao()),
				new SelectItem(FontePagadoraEnum.AERONAUTICA, FontePagadoraEnum.AERONAUTICA.getDescricao()) });

		SelectItemGroup grupoEstado = new SelectItemGroup("Estado");
		grupoEstado.setSelectItems(
				new SelectItem[] { new SelectItem(FontePagadoraEnum.SEPLAG, FontePagadoraEnum.SEPLAG.getDescricao()) });

		SelectItemGroup grupoMunicipal = new SelectItemGroup("Municipal");
		grupoMunicipal.setSelectItems(new SelectItem[] { new SelectItem(FontePagadoraEnum.PREFEITURA_ITAJUBA,
				FontePagadoraEnum.PREFEITURA_ITAJUBA.getDescricao()) });

		SelectItemGroup grupoSeguranca = new SelectItemGroup("Segurança Pública");
		grupoSeguranca.setSelectItems(new SelectItem[] {
				new SelectItem(FontePagadoraEnum.POLICIA_MILITAR, FontePagadoraEnum.POLICIA_MILITAR.getDescricao()),
				new SelectItem(FontePagadoraEnum.BOMBEIROS, FontePagadoraEnum.BOMBEIROS.getDescricao())

		});

		fontes = new ArrayList<>();
		fontes.add(grupoGovFederal);
		fontes.add(grupoForcarArmadas);
		fontes.add(grupoEstado);
		fontes.add(grupoSeguranca);
		fontes.add(grupoMunicipal);
	}

	public void preCadastro() {
		if (this.c == null) {
			this.c = new Cliente();
		}
		this.setTipos(tbr.todos());
		this.estados = Estado.ESTADOS;

		this.agrupaFontePagadora();
	}

	public void limpar() {
		this.c = new Cliente();
	}

	public void alteraFontePagadoraParaNull() {
		if (!this.c.isFontePagadoraINSS())
			this.c.setTipoBeneficio(null);
	}

	public void salvar() throws BusinessException {
		String aviso = "";
		this.context = FacesContext.getCurrentInstance();
		try {
			this.c = this.cc.salvar(this.c);
			context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
		} catch (Exception e) {
			if (cr.porCpf(c.getCpf()) != null)
				aviso = "Erro ao realizar cadastro, CPF " + "já cadastrado para o (a) cliente "
						+ cr.porCpf(c.getCpf()).getNome() + ".";
			else
				aviso = e.getMessage();
			msg = new FacesMessage(aviso);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}

	}

	/**
	 * Método que autocompleta clientes já cadastrados.
	 * 
	 * @author leandro
	 */
	public void validaEautoCompletaCadastroExistente() {
		this.context = FacesContext.getCurrentInstance();
		if (!this.cc.valida(c.getCpf())) {
			this.msg = new FacesMessage("Atenção: CPF Inválido.");
			this.msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			this.context.addMessage(null, msg);
		} else if (cr.porCpf(c.getCpf()) != null) {
			this.c = cr.porCpf(c.getCpf());
			this.msg = new FacesMessage("Cliente já cadastrado.");
			this.msg.setSeverity(FacesMessage.SEVERITY_WARN);
			this.context.addMessage(null, msg);
		}
	}

	/**
	 * Método usado para autocompletar a cidade digitada pelo usuário.
	 * 
	 * @param cidade
	 * @return
	 */
	public List<String> autoCompletaCidade(String cidade) {
		return this.cr.cidades(cidade);
	}

	/**
	 * Método usado para autocompletar o banco digitada pelo usuário.
	 * 
	 * @param banco
	 * @return
	 */
	public List<String> autoCompletaBanco(String banco) {
		return this.cr.bancos(banco);
	}

	public Cliente getC() {
		return c;
	}

	public void setC(Cliente c) {
		this.c = c;
	}

	public SexoEnum[] getSexo() {
		return SexoEnum.values();
	}

	// public FontePagadoraEnum[] getFonte() {
	// return FontePagadoraEnum.values();
	// }

	public List<TipoBeneficio> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoBeneficio> tipos) {
		this.tipos = tipos;
	}

	public Date getHoje() {
		return new Date();
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

	public List<String> getEstados() {
		return estados;
	}

	public List<SelectItem> getFontes() {
		return fontes;
	}

	public void setFontes(List<SelectItem> fontes) {
		this.fontes = fontes;
	}

}
