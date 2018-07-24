package br.com.idealitajuba.crm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.idealitajuba.crm.model.Agendamento;
import br.com.idealitajuba.crm.repository.AgendamentoRepos;

/**
 * Classe para converter uma string em um objeto e vice-versa.
 * @author Leandro Duarte
 *
 */
@FacesConverter(forClass = Agendamento.class)
public class AgendamentoConverter implements Converter{
	
	@Inject
	private AgendamentoRepos ar;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Agendamento retorno = null;
		if(value != null && !"".equals(value)) {
			retorno = ar.porId(new Long (value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Agendamento a = ((Agendamento) value);
			return a.getId() == null ? null : a.getId().toString();
		}
		return null;
	}

}
