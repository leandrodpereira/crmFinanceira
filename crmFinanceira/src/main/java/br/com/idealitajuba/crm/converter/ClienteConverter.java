package br.com.idealitajuba.crm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.idealitajuba.crm.model.Cliente;
import br.com.idealitajuba.crm.repository.ClienteRepos;

/**
 * Classe para converter uma string em um objeto e vice-versa.
 * @author Leandro Duarte
 *
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter{
	
	@Inject
	private ClienteRepos cr;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;
		if(value != null && !"".equals(value)) {
			retorno = cr.porId(new Long (value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Cliente c = ((Cliente) value);
			return c.getId() == null ? null : c.getId().toString();
		}
		return null;
	}

}
