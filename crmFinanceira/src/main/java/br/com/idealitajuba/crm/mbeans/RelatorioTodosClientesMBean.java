package br.com.idealitajuba.crm.mbeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import br.com.idealitajuba.crm.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioTodosClientesMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	public void emitir() {
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_todos_clientes.jasper", this.response,
				null, "Todos Clientes.pdf");

		Session session = (Session) manager;
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			facesContext.addMessage(null, new FacesMessage("A execução do relatório não retornou dados."));
		}
	}

}
