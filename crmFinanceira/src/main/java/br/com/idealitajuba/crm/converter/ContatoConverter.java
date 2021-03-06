package br.com.idealitajuba.crm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.idealitajuba.crm.model.Contato;
import br.com.idealitajuba.crm.repository.ContatoRepos;

/**
 * Classe para converter uma string em um objeto e vice-versa.
 * @author Leandro Duarte
 *
 */
@FacesConverter(forClass = Contato.class)
public class ContatoConverter implements Converter{
	
	@Inject
	private ContatoRepos cr;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Contato retorno = null;
		if(value != null && !"".equals(value)) {
			retorno = cr.porId(new Long (value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Contato c = ((Contato) value);
			return c.getId() == null ? null : c.getId().toString();
		}
		return null;
	}

}
