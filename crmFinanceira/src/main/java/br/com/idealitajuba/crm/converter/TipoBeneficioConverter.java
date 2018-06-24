package br.com.idealitajuba.crm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.idealitajuba.crm.model.TipoBeneficio;
import br.com.idealitajuba.crm.repository.TipoBeneficioRepos;

/**
 * Classe para converter uma string em um objeto e vice-versa.
 * @author Leandro Duarte
 *
 */
@FacesConverter(forClass = TipoBeneficio.class)
public class TipoBeneficioConverter implements Converter{
	
	@Inject
	private TipoBeneficioRepos tbr;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoBeneficio retorno = null;
		if(value != null && !"".equals(value)) {
			retorno = tbr.porId(new Long (value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			TipoBeneficio tb = ((TipoBeneficio) value);
			return tb.getId() == null ? null : tb.getId().toString();
		}
		return null;
	}

}
