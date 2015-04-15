package br.com.anderson.academico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.anderson.academico.dao.impl.DisciplinaDaoImpl;
import br.com.anderson.academico.modelo.Disciplina;
import br.com.anderson.academico.util.JpaUtil;

@FacesConverter(forClass=Disciplina.class)
public class DisciplinaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Disciplina retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		
		try {
			if (value != null && !"".equals(value)) {
				retorno = new DisciplinaDaoImpl().getById(new Long(value));
			}
			
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if (value instanceof Disciplina) {
			Disciplina retorno = (Disciplina) value;
			
			try {
				return retorno.getId().toString();
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

}
