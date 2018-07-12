package br.com.idealitajuba.crm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.idealitajuba.crm.model.Usuario;
import br.com.idealitajuba.crm.repository.UsuarioRepos;

/**
 * Classe para converter uma string em um objeto e vice-versa.
 * @author Leandro Duarte
 *
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{
	
	@Inject
	private UsuarioRepos ur;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		if(value != null && !"".equals(value)) {
			retorno = ur.porId(new Long (value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Usuario u = ((Usuario) value);
			return u.getId() == null ? null : u.getId().toString();
		}
		return null;
	}

}
