package br.com.idealitajuba.crm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.idealitajuba.crm.model.TipoContatoStatus;
import br.com.idealitajuba.crm.repository.TipoContatoStatusRepos;

/**
 * Classe para converter uma string em um objeto e vice-versa.
 * @author Leandro Duarte
 *
 */
@FacesConverter(forClass = TipoContatoStatus.class)
public class TipoContatoStatusConverter implements Converter{
	
	@Inject
	private TipoContatoStatusRepos tbr;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoContatoStatus retorno = null;
		if(value != null && !"".equals(value)) {
			retorno = tbr.porId(new Long (value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			TipoContatoStatus tcs = ((TipoContatoStatus) value);
			return tcs.getId() == null ? null : tcs.getId().toString();
		}
		return null;
	}

}
